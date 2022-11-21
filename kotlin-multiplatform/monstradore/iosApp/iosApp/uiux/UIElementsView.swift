//
//  UIElementsView.swift
//  iosApp
//
//  Created by Anja on 19.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct UIElementsView: View {
    @State private var speed = 0.0
    @State private var isEditing = false
    var body: some View {
        ScrollView {
            VStack
//            (
////                alignment: .leading
//            )
            {
                Text("Grundlegende Elemente")
                    .font(.title)
                Text("Text")
                    .font(.headline)
                Text("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.")
                Text("Bild")
                    .font(.headline)
                Image("sample")
                Text("Liste")
                    .font(.headline)
                List {
                    Text("Item: 0")
                    Text("Item: 1")
                    Text("Item: 2")
                    Text("Item: 3")
                    Text("Item: 4")
                }
                Text("Button")
                    .font(.headline)
                Button(action: {}) {
                    Text("Button")
                }
                Text("Icon Button")
                    .font(.headline)
                Button {
                    print("Edit button was tapped")
                } label: {
                    Image(systemName: "pencil")
                }
                Button {
                    print("Edit button was tapped")
                } label: {
                    Label("Edit", systemImage: "pencil")
                }
                Text("Elemente mit Statusverwaltung")
                    .font(.title)
                Text("Slider")
                    .font(.headline)
                Slider(
                            value: $speed,
                            in: 0...100,
                            onEditingChanged: { editing in
                                isEditing = editing
                            }
                        )
            }
            .padding()
        }
    }
}

//struct UIElementsView_Previews: PreviewProvider {
//    static var previews: some View {
//        UIElementsView()
//    }
//}
