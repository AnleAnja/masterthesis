//
//  PersistenceView.swift
//  iosApp
//
//  Created by Anja on 25.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PersistenceView : View {
    @State private var userName: String = ""
    @State private var users: [String] = UserStorage(storage: NSObject()).getUsers()
    private var storage: UserStorage = UserStorage(storage: NSObject())
    var body: some View {
        VStack{
            Text("Persistenz")
                .font(.headline)
            Text("Username eingeben")
            TextField("Name", text: $userName)
            Button(action: {
                users.append(userName)
                storage.saveUsers(users: users)
            }) {
                Text("User speichern")
            }
            ForEach(users) { user in
                Text(user)
            }
        }
        .padding()
        .frame(alignment: .topLeading)
    }
}

//struct PersistenceView_Previews: PreviewProvider {
//    static var previews: some View {
//        PersistenceView()
//    }
//}
