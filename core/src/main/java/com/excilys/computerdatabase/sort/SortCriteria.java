package com.excilys.computerdatabase.sort;

/**
 * @author Vincent Galloy
 *         The Class SortCriteria.
 */
public class SortCriteria {
    private SortColumn sortColumn;
    private SortDirection sortDirection;

    /**
     * Instantiates a new sort criteria.
     */
    public SortCriteria() {
        this.sortColumn = SortColumn.COMPUTER_ID;
        this.sortDirection = SortDirection.ASC;
    }

    /**
     * Instantiates a new sort criteria.
     *
     * @param sortColumn    the sort column
     * @param sortDirection the sort direction
     */
    public SortCriteria(SortColumn sortColumn, SortDirection sortDirection) {
        super();
        this.sortColumn = sortColumn;
        this.sortDirection = sortDirection;
    }

    public SortColumn getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(SortColumn sortColumn) {
        this.sortColumn = sortColumn;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public String toString() {
        return sortColumn.toString() + " " + sortDirection.toString();
    }
}
