//
//  FingerprintView.swift
//  iosApp
//
//  Created by Anja on 29.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import LocalAuthentication

struct FingerprintView: View {
    @State private var isUnlocked = false
    var body: some View {
        VStack {
            Text("Fingerabdruck / Face ID")
                .font(.headline)
            if isUnlocked {
                Text("Entsperrt")
            } else {
                Text("Gesperrt")
            }
        }
        .onAppear(perform: authenticate)
    }
    
    func authenticate() {
        let context = LAContext()
        var error: NSError?
        
        if context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) {
            let reason = "We need to unlock your data."
            
            context.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, localizedReason: reason) { success, authenticationError in
                // authentication has now completed
                if success {
                    isUnlocked = true
                } else {
                    print("Error with authentication")
                }
            }
        } else {
            print("Authentication not possible")
        }
    }
}

//struct FingerprintView_Previews: PreviewProvider {
//    static var previews: some View {
//        FingerprintView()
//    }
//}
