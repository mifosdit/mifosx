package org.mifosplatform.infrastructure.dataimport.data.group;

import java.util.ArrayList;

import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDate;
import org.mifosplatform.portfolio.group.data.GroupGeneralData;
//import org.mockito.cglib.core.Local;

public class CompactGroup {

    private final Integer id;

    private final String name;

    private final String officeName;

    private final ArrayList<Integer> activationDate;

    private final Boolean active;

    public CompactGroup(Integer id, String name, String officeName, ArrayList<Integer> activationDate, Boolean active) {
        this.id = id;
        this.name = name;
        this.activationDate = activationDate;
        this.officeName = officeName;
        this.active = active;
    }
    
    public CompactGroup(GroupGeneralData groupGeneralData) {
        
        this.id = groupGeneralData.getId().intValue();
        this.name = groupGeneralData.getName().trim();
        
        LocalDate activationDate = groupGeneralData.getActivationDate();
        this.activationDate = new ArrayList<Integer>();
        this.activationDate.add(activationDate.getYear());
        this.activationDate.add(activationDate.get(DateTimeFieldType.monthOfYear()));
        this.activationDate.add(activationDate.getDayOfMonth());
                
        this.officeName = groupGeneralData.getOfficeName();
        this.active = groupGeneralData.isActive();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOfficeName() {
        return officeName;
    }

    public ArrayList<Integer> getActivationDate() {
        return activationDate;
    }

    public Boolean isActive() {
        return active;
    }
}