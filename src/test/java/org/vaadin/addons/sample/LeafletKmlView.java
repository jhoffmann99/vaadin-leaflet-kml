package org.vaadin.addons.sample;

import com.vaadin.componentfactory.ToggleButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

/** Test view for manual and automated testing of the component.
 *
 */
@Route("map")
public class LeafletKmlView extends VerticalLayout {
   LeafletKml leaflet = new LeafletKml();

    public LeafletKmlView() {


        add(leaflet);
    }
}
