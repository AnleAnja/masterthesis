//
//  UIElementsView.swift
//  iosApp
//
//  Created by Anja on 19.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct UIElementsView: View {
    var body: some View {
        NavigationView {
            ScrollView {
                VStack(alignment: .leading)
                {
                    BasicElements()
                    ButtonElements()
                    StateElements()
                    PlatformspecificElements()
                    AdvancedElements()
                }
                .padding()
                .navigationBarTitleDisplayMode(.inline)
            }
        }
    }
}

//struct UIElementsView_Previews: PreviewProvider {
//    static var previews: some View {
//        UIElementsView()
//    }
//}

struct BasicElements: View {
    var body: some View {
        Text("Grundlegende Elemente")
            .font(.title)
            .multilineTextAlignment(.leading)
        Text("Text")
            .font(.headline)
            .multilineTextAlignment(.leading)
        Text("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.")
            .multilineTextAlignment(.leading)
        Text("Bild")
            .font(.headline)
            .multilineTextAlignment(.leading)
        Image("sample")
            .resizable()
            .aspectRatio(contentMode: .fit)
        Text("Liste")
            .font(.headline)
            .multilineTextAlignment(.leading)
        List {
            Text("Item: 0")
            Text("Item: 1")
            Text("Item: 2")
            Text("Item: 3")
            Text("Item: 4")
        }
        .frame(height: 280)
    }
}

struct ButtonElements: View {
    var body: some View {
        Text("Button")
            .font(.headline)
            .multilineTextAlignment(.leading)
        Button(action: {}) {
            Text("Button")
        }
        Text("Icon Button")
            .font(.headline)
            .multilineTextAlignment(.leading)
        Button { } label: {
            Image(systemName: "house")
        }
    }
}

struct StateElements: View {
    @State private var speed = 0.0
    @State private var isEditing = false
    @State private var textInput = ""
    @State private var toggleState = true
    var body: some View {
        Text("Elemente mit Statusverwaltung")
            .font(.title)
            .multilineTextAlignment(.leading)
        Text("Slider")
            .font(.headline)
            .multilineTextAlignment(.leading)
        Slider(
            value: $speed,
            in: 0...100,
            onEditingChanged: { editing in
                isEditing = editing
            }
        )
        Text("Textfeld")
            .font(.headline)
            .multilineTextAlignment(.leading)
        TextField("Texteingabe", text: $textInput)
        Text("Switch / Toggle")
            .font(.headline)
            .multilineTextAlignment(.leading)
        Toggle("", isOn: $toggleState)
    }
}

struct PlatformspecificElements: View {
    @State private var showView: Int? = 0
    var body: some View {
        Text("Plattformspezifische Elemente")
            .font(.title)
            .multilineTextAlignment(.leading)
            HStack {
                NavigationLink(destination: AndroidElementsView(), tag: 1, selection: $showView) {
                    EmptyView()
                }
                NavigationLink(destination: iOSElementsView(), tag: 2, selection: $showView) {
                    EmptyView()
                }
                Button("Android", action: {
                    self.showView = 1
                })
                Button("iOS", action: {
                    self.showView = 2
                })
        }
    }
}

struct AdvancedElements: View {
    @State private var showAlert = false
    var body: some View {
        Text("Fortgeschrittene Elemente")
            .font(.title)
            .multilineTextAlignment(.leading)
        HStack {
            Button(action: { }) {
                Text("Menu")
            }
            .contextMenu {
                Button { } label: {
                    Label("Menu Item", systemImage: "")
                }
            }
            Button("Dialog") {
                showAlert = true
            }
            .alert(isPresented: $showAlert) {
                Alert(
                    title: Text("Dialog Titel"),
                    message: Text("Dialog Text"),
                    dismissButton: .default(Text("OK"))
                )
            }
        }
    }
}
