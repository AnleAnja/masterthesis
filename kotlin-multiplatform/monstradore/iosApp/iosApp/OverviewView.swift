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
                    Section(header: Text(category.name)){
                        ForEach(category.features) { feature in
                            NavigationLink(destination: EmptyView()) {
                                Text(feature)
                                    .font(.body)
                            }
                        }
                    }
                }
            }
            .navigationTitle("monstradore")
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}
