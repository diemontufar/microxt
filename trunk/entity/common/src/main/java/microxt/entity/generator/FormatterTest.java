package microxt.entity.generator;

import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;

public class FormatterTest {

	public static void main(String[] args) {
		String code = "package common; "
				+ "\n"
				+ "import java.io.Serializable;"
				+ "\n"
				+ "/**"
				+ "* The persistent class for the SEQUENTIAL database table. "
				+ " */ "
				+ "@Entity "
				+ "@Table(name=\"SEQUENTIAL\") "
				+ " public class Sequential extends AbstractEntity implements Serializable, Cloneable, Multicompany{ "
				+ "@Id" + "@Column(name=\"SEQUENTIAL\"nullable=false) "
				+ "private String pk; " + "} ";

		CodeFormatter cf = new DefaultCodeFormatter();

		TextEdit te = cf.format(CodeFormatter.K_UNKNOWN, code, 0,
				code.length(), 0, null);
		IDocument dc = new Document(code);
		try {
			te.apply(dc);
			System.out.println(dc.get());
		} catch (MalformedTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}