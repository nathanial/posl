package posl.editorkit;

import java.awt.Color;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;

import posl.editorkit.util.BoxHighlightPainter;
import posl.engine.error.NullException;
import posl.engine.token.Token;

public class PairCaretListener implements CaretListener {

	private Highlighter highlighter;
	private DocumentImpl doc;
	private BoxHighlightPainter painter = new BoxHighlightPainter(Color.gray);

	public PairCaretListener(Highlighter highlighter, DocumentImpl document) {
		super();
		this.highlighter = highlighter;
		this.doc = document;
	}

	private Object pairHighlightTag = null;

	@Override
	public void caretUpdate(CaretEvent event) {
		// we need to store and remove the specific highlight that
		// we created. If we remove all the highlights, that affects
		// or ability to manually highlight code
		if (pairHighlightTag != null) {
			highlighter.removeHighlight(pairHighlightTag);
			pairHighlightTag = null;
		}
		Token pair;
		try {
			pair = (Token) doc.getTokenAt(event.getDot());
			DocAttributes attr = (DocAttributes)pair.getAttribute();
			if (attr.isPair()){
				pair = attr.getToken();
				pairHighlightTag = highlighter.addHighlight(pair.getStartOffset(),
					pair.getEndOffset(),painter);
			}
		} catch (NullException e1) {
			//swallow
		} catch (BadLocationException e) {
			// should NOT happen
			e.printStackTrace();
		}

	}

}
