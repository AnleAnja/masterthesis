//
//  PerformanceView.swift
//  iosApp
//
//  Created by Anja on 29.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

private func prime(n: Int32) async -> Int32 {
    PerformanceKt.prime(n: n)
}

struct PerformanceView: View {
    @State private var primeValue = ""
    @State private var speed = 0.0
    @State private var isEditing = false
    @State private var result = Int32(0)
    @State private var time = 0
    var body: some View {
        VStack {
            Text("Performance")
                .font(.headline)
            Text("Die wievielte Primzahl soll berechnet werden?")
            TextField("Zahl eingeben", text: $primeValue)
            Button(action: {
                let startTime = Int64(NSDate().timeIntervalSince1970 * 1000)
                Task {
                    result = await prime(n: Int32(primeValue) ?? 0)
                    let endTime = Int64(NSDate().timeIntervalSince1970 * 1000)
                    time = Int(endTime - startTime) / 1000
                }
            }) {
                Text("Berechnen")
            }
            Text("Ergebnis: \(result)")
            Text("Benötigte Zeit: \(time) Sekunden")
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

//struct PerformanceView_Previews: PreviewProvider {
//    static var previews: some View {
//        PerformanceView()
//    }
//}
