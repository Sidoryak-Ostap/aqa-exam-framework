package dataproviders;

import org.testng.annotations.DataProvider;

public class MuseumDataProvider {
    String filePathImage1 = System.getProperty("user.dir") + "/src/main/resources/images/museum1.jpg";
    String filePathImage2 = System.getProperty("user.dir") + "/src/main/resources/images/museum2.jpg";



    @DataProvider
    public Object[][] provideCreateMuseumData(){
        return new Object[][] {
                {"Ukr Museum Name 1", "Ukr Working Hrs 1", "Ukr address 1", "Eng Museum Name 1", "Eng Working Hrs 1", "Eng address 1", "https://www.fi-compass.eu/funds/amif", "+380984561234", "someorg@gmail.com", filePathImage1},
                {"Ukr Museum Name 2", "Ukr Working Hrs 2", "Ukr address 2", "Eng Museum Name 2", "Eng Working Hrs 2", "Eng address 2", "https://www.fi-compass.eu/funds/amif", "+380984567892", "someorg2@gmail.com", filePathImage2},
        };
    }
}
