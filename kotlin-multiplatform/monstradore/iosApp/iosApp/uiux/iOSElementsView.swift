//
//  iOSElementsView.swift
//  iosApp
//
//  Created by Anja on 22.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct iOSElementsView: View {
    @State private var searchText = ""
    @State private var selectedItem: Flavor = .item1

    enum Flavor: String, CaseIterable, Identifiable {
        case item1, item2, item3
        var id: Self { self }
    }
    
    var body: some View {
        VStack {
            Text("Search Bar")
                .font(.headline)
                .multilineTextAlignment(.leading)
            if #available(iOS 15.0, *) {
                List {
                    Text("Item: 0")
                    Text("Item: 1")
                    Text("Item: 2")
                    Text("Item: 3")
                    Text("Item: 4")
                }
                .searchable(text: $searchText)
            } else {
                Text("Ab iOS 15.0 verfügbar.")
            }
            Text("Picker")
                .font(.headline)
                .multilineTextAlignment(.leading)
            List {
                Picker("Items", selection: $selectedItem) {
                    Text("Item 1").tag(Flavor.item1)
                    Text("Item 2").tag(Flavor.item2)
                    Text("Item 3").tag(Flavor.item3)
                }
            }
        }
        .navigationBarTitleDisplayMode(.inline)
    }
}

//struct iOSElementsView_Previews: PreviewProvider {
//    static var previews: some View {
//        iOSElementsView()
//    }
//}
