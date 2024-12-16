package pages;

import com.microsoft.playwright.Page;

public class ProductPage extends CommonPage{
    public String pageHeader = "Products";
    public ProductPage(Page page) {
        super(page);
    }

    public void selectProductByName(String name){
        selectItemByLabel(name);
    }

    public void iShouldSeeProductsPage(String pageTitle) {
        verifyPageTitle(pageTitle);
    }
}
