import SwiftUI
import shared

struct MatchGameScreen: View {
    @ObservedObject private var viewModelWrapper = IOSMatchGameViewModel(repository: MatchGameRepositoryImpl())

    var body: some View {
        VStack {
            Text("Match the primary language translations with the secondary language translations:")
                .font(.title2)
            .padding()

            FlowLayout(textElements: viewModelWrapper.textElements, language: "en")
            Spacer().frame(height: 16)
            FlowLayout(textElements: viewModelWrapper.textElements, language: "tr")
        }
            .padding()
    }
}

struct FlowLayout: View {
    let textElements: [TextElement]
    let language: String

    var body: some View {
        VStack {
            ForEach(textElements, id: \.id) { textElement in
                if let translation = textElement.translations[language] {
                    Text(translation)
                        .padding()
                        .background(Color.gray.opacity(0.2))
                        .cornerRadius(8)
                        .onTapGesture {
                            // Handle click event
                        }
                }
        }
        }
    }
}
