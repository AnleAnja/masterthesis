import SwiftUI
import shared

extension String: Identifiable {
    public typealias ID = Int
    public var id: Int {
        return hash
    }
}

@ViewBuilder
func element(_ feature: String, _ desired: String, _ dest: some View) -> some View {
    if(feature == desired) {
        NavigationLink(destination: dest) {
            Text(feature)
                .font(.body)
            }
    }
}

struct OverviewView: View {
    let categories = Features.shared.overview
    
    @ViewBuilder
    func element(_ feature: String, _ desired: String, _ dest: some View) -> some View {
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
                            element(feature, "Reichhaltige UI Elemente", UIElementsView())
                            element(feature, "Interaktionsdesign", InteractionDesignView())
                            element(feature, "Gesten", GestureView())
                            element(feature, "Navigation", NavigationElementsView())
                            element(feature, "Eingabemethoden", InputMethodsView())
                            element(feature, "Multimedia", MultimediaView())
                            element(feature, "Animationen", AnimationsView())
                            //element(feature, "2D und 3D Grafiken", 3DGraphicsView())
                            element(feature, "Netzwerkcalls", NetworkCallView())
                            element(feature, "Dateizugriff", FileAccessView())
                            element(feature, "Persistierung", PersistenceView())
                            element(feature, "Zugriff auf native Anwendungen", AppAccessView())
                            element(feature, "Kamera", CameraView())
                            element(feature, "GPS", GPSView())
                            element(feature, "Beschleunigung", AccelerationView())
                            element(feature, "Fingerabdruck / Face ID", FingerprintView())
                            element(feature, "Primzahlberechnung", PerformanceView())
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
