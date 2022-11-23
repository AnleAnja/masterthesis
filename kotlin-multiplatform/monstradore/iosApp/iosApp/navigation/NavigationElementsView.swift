//
//  NavigationView.swift
//  iosApp
//
//  Created by Anja on 23.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct NavigationView: View {
    @State private var favoriteColor = "Red"
    var colors = ["Red", "Green", "Blue"]
    
    var body: some View {
        VStack {
            Picker("What is your favorite color?", selection: $favoriteColor) {
                ForEach(colors, id: \.self) {
                    Text($0)
                }
            }
            .pickerStyle(.segmented)
            
            Text("Value: \(favoriteColor)")
        }
    }
}

//struct NavigationView_Previews: PreviewProvider {
//    static var previews: some View {
//        NavigationView()
//    }
//}
