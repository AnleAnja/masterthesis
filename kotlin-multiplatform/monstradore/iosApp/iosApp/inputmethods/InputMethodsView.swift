//
//  InputMethodsView.swift
//  iosApp
//
//  Created by Anja on 22.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct InputMethodsView: View {
    @State private var textField: String = ""
    @State private var hiddenTextField: String = ""
    var body: some View {
        VStack {
            Text("Texteingabe")
                .font(.headline)
                .multilineTextAlignment(.leading)
            TextField("Label", text: $textField)
            Text("Passworteingabe")
                .font(.headline)
                .multilineTextAlignment(.leading)
            SecureField("Password Label", text: $hiddenTextField)
        }
        .padding()
        .navigationBarTitleDisplayMode(.inline)
    }
}

//struct InputMethodsView_Previews: PreviewProvider {
//    static var previews: some View {
//        InputMethodsView()
//    }
//}
