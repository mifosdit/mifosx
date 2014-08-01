package org.mifosplatform.infrastructure.dataimport.api;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mifosplatform.infrastructure.dataimport.services.ImportPlatformService;
import org.mifosplatform.infrastructure.dataimport.services.TemplatePlatformService;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.api.ClientApiConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataParam;

@Path("/{entityType}/import")
@Component
@Scope("singleton")
public class DataImportApiResource {

	private final PlatformSecurityContext context;
	private final TemplatePlatformService templatePlatformService;
	private final ImportPlatformService importPlatformService;

	@Autowired
	public DataImportApiResource(final PlatformSecurityContext context,
			final TemplatePlatformService templatePlatformService,
			ImportPlatformService importPlatformService) {

		this.context = context;
		this.templatePlatformService = templatePlatformService;
		this.importPlatformService = importPlatformService;
	}

	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public Response getTemplate(
			@PathParam("entityType") final String entityType,
			@PathParam("clientTypeId") final int clientTypeId) {

		this.context.authenticatedUser().validateHasReadPermission(
				ClientApiConstants.CLIENT_RESOURCE_NAME);

		Response response = null;

		if (entityType.equals("clients"))
			response = this.templatePlatformService
					.getClientImportTemplate(clientTypeId);
		else if (entityType.equals("groups"))
			response = this.templatePlatformService
					.getGroupImportTemplate();
		else if (entityType.equals("savings"))
			response = this.templatePlatformService
					.getSavingImportTemplate();
		else if (entityType.equals("savingtransactions"))
			response = this.templatePlatformService
					.getSavingsTransactionImportTemplate();
		else if (entityType.equals("loans"))
			response = this.templatePlatformService
					.getLoanImportTemplate();
		else if (entityType.equals("loanrepayments"))
			response = this.templatePlatformService
					.getLoanRepaymentImportTemplate();
		else {

		}

		return response;
	}

	@POST
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public Response importFromTemplate(
			@PathParam("entityType") final String entityType,
			@FormDataParam("file") final InputStream uploadedInputStream,
			@SuppressWarnings("unused") @FormDataParam("file") final FormDataContentDisposition fileDetails,
			@SuppressWarnings("unused") @FormDataParam("file") final FormDataBodyPart bodyPart,
			@SuppressWarnings("unused") @FormDataParam("clientTypeId") final int clientTypeId) {

		this.context.authenticatedUser().validateHasReadPermission(
				ClientApiConstants.CLIENT_RESOURCE_NAME);

		Response response = null;

		if (entityType.equals("clients"))
			response = this.importPlatformService
					.importClientsFromTemplate(uploadedInputStream);
		else if (entityType.equals("groups"))
			response = this.importPlatformService
					.importGroupsFromTemplate(uploadedInputStream);
		else if (entityType.equals("savings"))
			response = this.importPlatformService
					.importSavingsFromTemplate(uploadedInputStream);
		else if (entityType.equals("savingtransactions"))
			response = this.importPlatformService
					.importSavingsTransactionFromTemplate(uploadedInputStream);
		else if (entityType.equals("loans"))
			response = this.importPlatformService
					.importLoansFromTemplate(uploadedInputStream);
		else if (entityType.equals("loanrepayments"))
			response = this.importPlatformService
					.importLoanRepaymentFromTemplate(uploadedInputStream);
		else {

		}

		return response;
	}

	@GET
	@Path("test")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String importTemplate(
			@PathParam("entityType") final String entityType) {

		return entityType;
	}

}