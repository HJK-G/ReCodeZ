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
		CodeFile file = new CodeFile(code);
		CodeFileTraverser fileTraverser = new CodeFileTraverser(file);
		ErrorChecker parentheses = new ParenthesesErrorChecker();
		ErrorChecker colons = new ColonErrorChecker();

		parentheses.setSuccessor(colons);

		fileTraverser.traverse(parentheses);

		int size = ErrorChecker.getMessages().size();
		String[] messages;
		if (size == 0) {
			messages = new String[size];
		}
		else {
			messages = new String[size];
			for (int i = 0; i < size; i++) {
				messages[i] = ErrorChecker.getMessages().remove();
			}
		}

		return messages;

	}
}
