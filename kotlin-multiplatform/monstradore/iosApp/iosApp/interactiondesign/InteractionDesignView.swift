//
//  InteractionDesignView.swift
//  iosApp
//
//  Created by Anja on 22.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct InteractionDesignView: View {
    @State private var listElements = ["Index 0", "Index 1", "Index 2"]
    var body: some View {
        List {
            ForEach(listElements, id: \.self) { element in
                Text(element)
            }
            .onDelete { indexSet in
                listElements.remove(atOffsets: indexSet)
            }
        }
    }
}

//struct InteractionDesignView_Previews: PreviewProvider {
//    static var previews: some View {
//        InteractionDesignView()
//    }
//}
