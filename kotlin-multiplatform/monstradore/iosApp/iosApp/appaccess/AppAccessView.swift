//
//  AppAccessView.swift
//  iosApp
//
//  Created by Anja on 28.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Contacts

struct AppAccessView: View {
    @State private var name = ""
    @State private var number = ""
    var body: some View {
        VStack {
            Text("Zugriff auf Kontakte Anwendung")
                .font(.headline)
            Button(action: {
                Task.init {
                    await fetchContacts()
                }
            }) {
                Text("Kontakt auswählen")
            }
            Text(name)
            Text(number)
        }
    }
    
    func fetchContacts() async {
        let store = CNContactStore()
        let keys = [CNContactGivenNameKey, CNContactPhoneNumbersKey] as [CNKeyDescriptor]
        let fetchRequest = CNContactFetchRequest(keysToFetch: keys)
        do {
            try store.enumerateContacts(with: fetchRequest, usingBlock: { contact, result in
                name = contact.givenName + contact.familyName
                number = contact.phoneNumbers[0].value.stringValue
            })
        }
        catch {
            print("Error")
        }
    }
    
}

struct AppAccessView_Previews: PreviewProvider {
    static var previews: some View {
        AppAccessView()
    }
}
