package nl.iobyte.languagemanager.generic.menu.multi;

import nl.iobyte.languagemanager.generic.menu.basic.Menu;

public class MenuPage extends Menu {

    private final MultiMenu menu;

    public MenuPage(String title, int size, MultiMenu menu) {
        super(title, size);
        this.menu = menu;
    }

    public MenuPage(String title, int size, boolean locked, MultiMenu menu) {
        super(title, size, locked);
        this.menu = menu;
    }

    /**
     * Get parent of page
     * @return MultiMenu
     */
    public MultiMenu getParent() {
        return menu;
    }

}
