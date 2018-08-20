package io.kidspython;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kidspython.handlers.CodeFileTraverser;
import io.kidspython.handlers.ColonErrorChecker;
import io.kidspython.handlers.ErrorChecker;
import io.kidspython.handlers.ParenthesesErrorChecker;

@RestController
public class CoreController {
	@RequestMapping("/check")
	public String[] check(@RequestParam(value = "code", defaultValue = "") String code) {
		CodeFile file = new CodeFile(code, true);
		CodeFileTraverser fileTraverser = new CodeFileTraverser(file);
		ErrorChecker parentheses = new ParenthesesErrorChecker();
		ErrorChecker colons = new ColonErrorChecker();

		parentheses.setSuccessor(colons);

		fileTraverser.traverse(parentheses);

		String[] messages = new String[ErrorChecker.getMessages().size()];
		for (int i = 0; i < ErrorChecker.getMessages().size(); i++) {
			messages[i] = ErrorChecker.getMessages().remove();
		}
		
		return messages;

	}
}
