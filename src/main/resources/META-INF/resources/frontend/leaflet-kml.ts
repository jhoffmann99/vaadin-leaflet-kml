import { css, customElement, html, LitElement, property } from 'lit-element';
import * as L from 'leaflet';
import 'leaflet-kml';

@customElement('jhoffmann-leaflet')
export class LeafletKml extends LitElement {
  @property({ type: Number })
  width = 600;

  @property({ type: Number })
  height = 400;

  @property({ type: String })
  kmlUrl = '';

  private map!: L.Map;

  static get styles() {
    return css`
      :host {
        display: block;
        position: relative;
      }

      #map {
        width: 100%;
        height: 100%;
      }
    `;
  }

  render() {
    return html`<div id="map"></div>`;
  }

  firstUpdated() {
    // Initialize the Leaflet map
    this.map = new L.Map('map', { center: new L.LatLng(58.4, 43.0), zoom: 11 });

    // Set the size of the map
    this.shadowRoot!.querySelector('#map')!.setAttribute('style', `width:${this.width}px; height:${this.height}px`);

    // Add the KML layer to the map
    const osm = L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: 'Map data © <a href="http://openstreetmap.org">OpenStreetMap</a> contributors'
    });

    map.addLayer(osm);

    if (this.kmlUrl) {
      L.KML(this.kmlUrl).addTo(this.map);
    }

    // Add a click event listener to the map
    this.map.on('click', (event: L.LeafletMouseEvent) => {
      const latitude = event.latlng.lat;
      const longitude = event.latlng.lng;

      this.dispatchEvent(new CustomEvent('map-click', { detail: { latitude, longitude } }));
    });
  }
}