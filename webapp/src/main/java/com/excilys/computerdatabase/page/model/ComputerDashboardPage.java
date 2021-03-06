package com.excilys.computerdatabase.page.model;

import com.excilys.computerdatabase.dto.model.ComputerDto;

import java.util.List;

/**
 * @author Vincent Galloy
 *         The Class ComputerDashboardPage.
 */
public class ComputerDashboardPage {
    private long page;
    private long size;
    private String search;
    private String sortColumn;
    private String sortDirection;
    private List<ComputerDto> computers;
    private long pageMax;
    private String numberOfComputer;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public List<ComputerDto> getComputers() {
        return computers;
    }

    public void setComputers(List<ComputerDto> computers) {
        this.computers = computers;
    }

    public long getPageMax() {
        return pageMax;
    }

    public void setPageMax(long pageMax) {
        this.pageMax = pageMax;
    }

    public String getNumberOfComputer() {
        return numberOfComputer;
    }

    public void setNumberOfComputer(String numberOfComputer) {
        this.numberOfComputer = numberOfComputer;
    }

    @Override
    public String toString() {
        return "DashboardPage [page=" + page + ", size=" + size + ", search="
                + search + ", sortColumn=" + sortColumn + ", sortDirection="
                + sortDirection + ", computers=" + computers + ", pageMax="
                + pageMax + ", numberOfComputer=" + numberOfComputer + "]";
    }
}
