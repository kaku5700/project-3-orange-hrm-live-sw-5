package resources.testdata;

import org.testng.annotations.DataProvider;

public class LoginTestData {
    @DataProvider(name = "data set")
    public Object[][] getData()
    {
        Object [] [] data = new Object[][]
                {
                        { " " , " " , "Required"},
                        { "test123@gmail.com" ," " ,"Required" },
                        { " " , "test123" ,   "Required"},
                        {"test123@gmail.com" ,"test123",  "Invalid credentials"}

                };
        return data;
    }
}
