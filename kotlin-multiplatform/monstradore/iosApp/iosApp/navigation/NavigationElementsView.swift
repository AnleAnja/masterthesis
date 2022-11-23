//
//  NavigationElementsView.swift
//  iosApp
//
//  Created by Anja on 23.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NavigationElementsView: View {
    var titles = Navigation.shared.tabTitles
    @State private var currentTab = "Tab 1"
    var body: some View {
        VStack {
            Picker("Auswahl", selection: $currentTab) {
                ForEach(titles, id: \.self) {
                    Text($0)
                }
            }
            .pickerStyle(.segmented)
            
            Text(currentTab)
            TabView {
                Text("Tab 1")
                    .tabItem {
                        Label("Tab 1", systemImage: "house")
                    }
                
                Text("Tab 2")
                    .tabItem {
                        Label("Tab 2", systemImage: "house")
                    }
                Text("Tab 3")
                    .tabItem {
                        Label("Tab 3", systemImage: "house")
                    }
            }
        }
        .navigationBarTitleDisplayMode(.inline)
    }
}

//struct NavigationElementsView_Previews: PreviewProvider {
//    static var previews: some View {
//        NavigationElementsView()
//    }
//}
