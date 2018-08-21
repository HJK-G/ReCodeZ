package com.recodez;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recodez.framework.CodeFile;
import com.recodez.handlers.CodeFileTraverser;
import com.recodez.handlers.ColonErrorChecker;
import com.recodez.handlers.ErrorChecker;
import com.recodez.handlers.ParenthesesErrorChecker;

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
		int size = ErrorChecker.getMessages().size();
		for (int i = 0; i < size; i++) {
			messages[i] = ErrorChecker.getMessages().remove();
		}

		return messages;

	}
}
