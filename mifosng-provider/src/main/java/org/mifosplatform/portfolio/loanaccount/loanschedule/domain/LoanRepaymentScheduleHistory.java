/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.loanaccount.loanschedule.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.mifosplatform.portfolio.loanaccount.domain.Loan;
import org.mifosplatform.portfolio.loanaccount.rescheduleloan.domain.LoanRescheduleRequest;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "m_loan_repayment_schedule_history")
public class LoanRepaymentScheduleHistory extends AbstractPersistable<Long> {

    @SuppressWarnings("unused")
    @ManyToOne(optional = false)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @SuppressWarnings("unused")
    @OneToOne(optional = true)
    @JoinColumn(name = "loan_reschedule_request_id")
    private LoanRescheduleRequest loanRescheduleRequest;

    @SuppressWarnings("unused")
    @Column(name = "installment", nullable = false)
    private Integer installmentNumber;

    @SuppressWarnings("unused")
    @Temporal(TemporalType.DATE)
    @Column(name = "fromdate", nullable = true)
    private Date fromDate;

    @SuppressWarnings("unused")
    @Temporal(TemporalType.DATE)
    @Column(name = "duedate", nullable = false)
    private Date dueDate;

    @SuppressWarnings("unused")
    @Column(name = "principal_amount", scale = 6, precision = 19, nullable = true)
    private BigDecimal principal;

    @SuppressWarnings("unused")
    @Column(name = "interest_amount", scale = 6, precision = 19, nullable = true)
    private BigDecimal interestCharged;

    @SuppressWarnings("unused")
    @Column(name = "fee_charges_amount", scale = 6, precision = 19, nullable = true)
    private BigDecimal feeChargesCharged;

    @SuppressWarnings("unused")
    @Column(name = "penalty_charges_amount", scale = 6, precision = 19, nullable = true)
    private BigDecimal penaltyCharges;

    @SuppressWarnings("unused")
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdOnDate;

    @SuppressWarnings("unused")
    @ManyToOne
    @JoinColumn(name = "createdby_id")
    private AppUser createdByUser;

    @SuppressWarnings("unused")
    @ManyToOne
    @JoinColumn(name = "lastmodifiedby_id")
    private AppUser lastModifiedByUser;

    @SuppressWarnings("unused")
    @Temporal(TemporalType.DATE)
    @Column(name = "lastmodified_date")
    private Date lastModifiedOnDate;

    @Column(name = "version")
    @SuppressWarnings("unused")
    private Integer version;

    /**
     * LoanRepaymentScheduleHistory constructor
     **/
    protected LoanRepaymentScheduleHistory() {}

    /**
     * LoanRepaymentScheduleHistory constructor
     **/
    private LoanRepaymentScheduleHistory(final Loan loan, final LoanRescheduleRequest loanRescheduleRequest,
            final Integer installmentNumber, final Date fromDate, final Date dueDate, final BigDecimal principal,
            final BigDecimal interestCharged, final BigDecimal feeChargesCharged, final BigDecimal penaltyCharges,
            final Date createdOnDate, final AppUser createdByUser, final AppUser lastModifiedByUser, final Date lastModifiedOnDate,
            final Integer version) {

        this.loan = loan;
        this.loanRescheduleRequest = loanRescheduleRequest;
        this.installmentNumber = installmentNumber;
        this.fromDate = fromDate;
        this.dueDate = dueDate;
        this.principal = principal;
        this.interestCharged = interestCharged;
        this.feeChargesCharged = feeChargesCharged;
        this.penaltyCharges = penaltyCharges;
        this.createdOnDate = createdOnDate;
        this.createdByUser = createdByUser;
        this.lastModifiedByUser = lastModifiedByUser;
        this.lastModifiedOnDate = lastModifiedOnDate;
        this.version = version;
    }

    /**
     * @return an instance of the LoanRepaymentScheduleHistory class
     **/
    public static LoanRepaymentScheduleHistory instance(final Loan loan, final LoanRescheduleRequest loanRescheduleRequest,
            final Integer installmentNumber, final Date fromDate, final Date dueDate, final BigDecimal principal,
            final BigDecimal interestCharged, final BigDecimal feeChargesCharged, final BigDecimal penaltyCharges,
            final Date createdOnDate, final AppUser createdByUser, final AppUser lastModifiedByUser, final Date lastModifiedOnDate,
            final Integer version) {

        return new LoanRepaymentScheduleHistory(loan, loanRescheduleRequest, installmentNumber, fromDate, dueDate, principal,
                interestCharged, feeChargesCharged, penaltyCharges, createdOnDate, createdByUser, lastModifiedByUser, lastModifiedOnDate,
                version);

    }

}
