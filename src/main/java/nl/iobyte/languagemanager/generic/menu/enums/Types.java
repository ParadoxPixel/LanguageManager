package nl.iobyte.languagemanager.generic.menu.enums;

import org.bukkit.event.inventory.ClickType;

import java.util.Arrays;
import java.util.List;

public enum Types {

    LEFT(ClickType.LEFT, ClickType.SHIFT_LEFT),
    RIGHT(ClickType.RIGHT, ClickType.SHIFT_RIGHT),
    NORMAL(ClickType.LEFT, ClickType.RIGHT),
    SHIFT(ClickType.SHIFT_LEFT, ClickType.SHIFT_RIGHT),
    WINDOW_BORDER(ClickType.WINDOW_BORDER_LEFT, ClickType.WINDOW_BORDER_RIGHT);

    private final ClickType[] types;
    Types(ClickType... types) {
        this.types = types;
    }

    /**
     * Get possible click types
     * @return ClickType[]
     */
    public ClickType[] getTypes() {
        return types;
    }

    /**
     * Get possible click types as list
     * @return List<ClickType>
     */
    public List<ClickType> getTypesList() {
        return Arrays.asList(types);
    }

}
