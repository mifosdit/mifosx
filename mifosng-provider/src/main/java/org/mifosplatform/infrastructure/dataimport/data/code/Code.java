package org.mifosplatform.infrastructure.dataimport.data.code;

public class Code {

    private final transient Integer rowIndex;

    private final transient String status;

    private final String name;

    public Code(final String name, final Integer rowIndex, final String status) {

        this.name = name;
        this.rowIndex = rowIndex;
        this.status = status;
    }

    public Integer getRowIndex() {
        return this.rowIndex;
    }

    public String getStatus() {
        return this.status;
    }

    public String getName() {
        return this.name;
    }
}