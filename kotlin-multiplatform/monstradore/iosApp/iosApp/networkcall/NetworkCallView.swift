//
//  NetworkCallView.swift
//  iosApp
//
//  Created by Anja on 24.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NetworkCallView: View {
    @State private var result = "Loading"
    var body: some View {
        VStack {
            Text("Netzwerkcall")
                .font(.headline)
                .multilineTextAlignment(.leading)
            Text(result)
                .multilineTextAlignment(.leading)
                .onAppear {
                    Task {
                        let data = try await NetworkApi().getResponse()
                        result = data
                    }
                }
        }
    }
}

//struct NetworkCallView_Previews: PreviewProvider {
//    static var previews: some View {
//        NetworkCallView()
//    }
//}
