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
    var body: some View {
        NavigationView {
            List {
                ForEach(categories, id: \.name) { category in
                    Section(
                        header: Text(category.name)
                            .font(.headline)
                    ){
                        ForEach(category.features) { feature in
                            if(feature == "Reichhaltige UI Elemente") {
                                NavigationLink(destination: UIElementsView()) {
                                    Text(feature)
                                        .font(.body)
                                }
                            }
                            if(feature == "Interaktionsdesign") {
                                NavigationLink(destination: InteractionDesignView()) {
                                    Text(feature)
                                        .font(.body)
                                }
                            }
                            if(feature == "Gesten") {
                                NavigationLink(destination: GestureView()) {
                                Text(feature)
                                        .font(.body)
                                }
                            }
                            if(feature == "Navigation") {
                                NavigationLink(destination: NavigationElementsView()) {
                                    Text(feature)
                                        .font(.body)
                                }
                          }
                            if(feature == "Eingabemethoden") {
                                NavigationLink(destination: InputMethodsView()) {
                                    Text(feature)
                                        .font(.body)
                                }
                           }
                          if(feature == "Multimedia") {
                                NavigationLink(destination: MultimediaView()) {
                                    Text(feature)
                                        .font(.body)
                                }
                          }
                          if(feature == "Animationen") {
                                NavigationLink(destination: AnimationsView()) {
                                    Text(feature)
                                        .font(.body)
                                }
                         }
                         if(feature == "Netzwerkcalls") {
                                NavigationLink(destination: NetworkCallView()) {
                                Text(feature)
                                        .font(.body)
                                }
                         }
                         if(feature == "Dateizugriff") {
                                NavigationLink(destination: FileAccessView()) {
                                Text(feature)
                                        .font(.body)
                                }
                         }
                         if(feature == "Persistierung") {
                                NavigationLink(destination: PersistenceView()) {
                                    Text(feature)
                                        .font(.body)
                                }
                         }
                    }
                }
                .navigationTitle("monstradore")
            }
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}
