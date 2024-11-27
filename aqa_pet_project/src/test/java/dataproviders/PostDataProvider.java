package dataproviders;

import org.testng.annotations.DataProvider;

public class PostDataProvider {


    String filePathImage1 = System.getProperty("user.dir") + "/src/main/resources/images/Landscape-Color.jpg";
    String filePathImage2 = System.getProperty("user.dir") + "/src/main/resources/images/field-6574455_640.jpg";
    String filePathImage3 = System.getProperty("user.dir") + "/src/main/resources/images/tree-736885_640.jpg";


    @DataProvider
    public Object[][] provideCreatePostData(){
        return new Object[][] {
                {"Ukr Title 1", "Ukr Short Description 1", "Ukr Full Description 1", "Eng Title 1", "Eng Short Description 1", "Eng Full Description 1", filePathImage1 },
                {"Ukr Title 2", "Ukr Short Description 2", "Ukr Full Description 2", "Eng Title 2", "Eng Short Description 2", "Eng Full Description 2", filePathImage2 },
                {"Ukr Title 3", "Ukr Short Description 3", "Ukr Full Description 3", "Eng Title 3", "Eng Short Description 3", "Eng Full Description 3", filePathImage3 }
        };
    }

    @DataProvider
    public Object[][] provideUpdatePostData() {
        return new Object[][]{
                {"Update Ukr Title 1", "Update Ukr Short Description 1", "Update Ukr Full Description 1", "Update Eng Title 1", "Update Eng Short Description 1", "Update Eng Full Description 1", filePathImage1},
        };
    }
}
