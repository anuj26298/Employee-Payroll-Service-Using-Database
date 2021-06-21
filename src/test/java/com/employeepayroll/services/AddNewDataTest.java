package com.employeepayroll.services;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class AddNewDataTest {
    @Test
    public void givenDataList_WhenAddedIntoDatabase_ReturnTrue() throws SQLException {
        AddNewData addNewData = new AddNewData();
        RetrieveData retrieveData = new RetrieveData();

        String query = "insert into employee_payroll values(5,'Anuj','30000','2021-04-26')";
        addNewData.addData(query);
        Assert.assertEquals(5, retrieveData.ReadTableData("select * from employee_payroll")
                .size());
    }
}
