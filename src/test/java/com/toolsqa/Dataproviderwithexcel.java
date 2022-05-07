package com.toolsqa;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataproviderwithexcel {

    @Test
    public void Dataproviderexcel(String text1, String text2){

        System.out.println(text1 + "value is " +text2);

    }

    @DataProvider(name = "excel")
    public Object[][] getdataExcel(){

    }

}
