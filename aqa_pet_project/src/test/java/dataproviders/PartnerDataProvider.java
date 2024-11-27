package dataproviders;

import org.testng.annotations.DataProvider;

public class PartnerDataProvider {

    String filePathImage1 = System.getProperty("user.dir") + "/src/main/resources/images/partner1.jpg";
    String filePathImage2 = System.getProperty("user.dir") + "/src/main/resources/images/partner2.jpg";



    @DataProvider
    public Object[][] provideCreatePartnerData(){
        return new Object[][] {
                {"Partner 1", "https://uk.wikipedia.org/",  filePathImage1 },
                {"Partner 2", "https://uk.wikipedia.org/", filePathImage2 },
        };
    }

}
