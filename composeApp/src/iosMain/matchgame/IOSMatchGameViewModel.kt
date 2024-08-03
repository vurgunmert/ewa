import Foundation
import shared

class IOSMatchGameViewModel: ObservableObject {
    private let viewModel: MatchGameViewModel

    @Published var textElements: [TextElement] = []

    init(repository: MatchGameRepository) {
        viewModel = SharedMatchGameViewModel(repository: repository)
        observeViewModel()
        viewModel.loadTextElements()
    }

    private func observeViewModel() {
        viewModel.textElements.collect { [weak self] elements in
            self?.textElements = elements
        }
    }
}
