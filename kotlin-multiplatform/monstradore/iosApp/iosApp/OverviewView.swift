import SwiftUI
import shared

extension String: Identifiable {
    public typealias ID = Int
    public var id: Int {
        return hash
    }
}

struct OverviewView: View {
    let categories = Features.shared.overview
    
    @ViewBuilder
    func element(_ desired: String, _ dest: some View) -> some View {
        if(feature == desired) {
            NavigationLink(destination: dest) {
                Text(feature)
                    .font(.body)
            }
        }
    }
    
    var body: some View {
        NavigationView {
            List {
                ForEach(categories, id: \.name) { category in
                    Section(
                        header: Text(category.name)
                            .font(.headline)
                    ){
                        ForEach(category.features) { feature in
                            element("Reichhaltige UI Elemente", UIElementsView())
                            element("Interaktionsdesign", InteractionDesignView())
                            element("Gesten", GestureView())
                            element("Navigation", NavigationElementsView())
                            element("Eingabemethoden", InputMethodsView())
                            element("Multimedia", MultimediaView())
                            element("Animationen", AnimationsView())
                            //element("2D und 3D Grafiken", 3DGraphicsView())
                            element("Netzwerkcalls", NetworkCallView())
                            element("Dateizugriff", FileAccessView())
                            element("Persistierung", PersistenceView())
                            element("Zugriff auf native Anwendungen", AppAccessView())
                            //element("Kamera", CameraView())
                            //element("GPS", GPSView())
                            //element("Beschleunigung", AccelerationView())
                            //element("Fingerabdruck / Face ID", FingerprintView())
                            //element("Primzahlberechnung", PerformanceView())
                        }
                    }
                    .navigationTitle("monstradore")
                }
            }
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}
