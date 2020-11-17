// Generated from ex1.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ex1 extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FRENCH=1, ENG=2, CATCH=3, NORMAL_ID=4;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"FRENCH", "ENG", "CATCH", "NORMAL_ID"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FRENCH", "ENG", "CATCH", "NORMAL_ID"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ex1(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ex1.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0:
			FRENCH_action((RuleContext)_localctx, actionIndex);
			break;
		case 1:
			ENG_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void FRENCH_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			System.out.println("french");
			break;
		}
	}
	private void ENG_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			System.out.println("english");
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\6N\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2 \n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\5\3>\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\7\5H\n\5\f\5\16"+
		"\5K\13\5\3\5\3\5\2\2\6\3\3\5\4\7\5\t\6\3\2\4\5\2C\\aac|\6\2\62;C\\aac"+
		"|\2`\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\3\37\3\2\2\2\5=\3"+
		"\2\2\2\7A\3\2\2\2\tE\3\2\2\2\13\f\7f\2\2\f \7g\2\2\r \7\u00e2\2\2\16\17"+
		"\7n\2\2\17 \7g\2\2\20\21\7n\2\2\21 \7c\2\2\22\23\7g\2\2\23 \7v\2\2\24"+
		"\25\7k\2\2\25 \7n\2\2\26\27\7n\2\2\27\30\7g\2\2\30 \7u\2\2\31\32\7w\2"+
		"\2\32 \7p\2\2\33\34\7g\2\2\34 \7p\2\2\35\36\7f\2\2\36 \7w\2\2\37\13\3"+
		"\2\2\2\37\r\3\2\2\2\37\16\3\2\2\2\37\20\3\2\2\2\37\22\3\2\2\2\37\24\3"+
		"\2\2\2\37\26\3\2\2\2\37\31\3\2\2\2\37\33\3\2\2\2\37\35\3\2\2\2 !\3\2\2"+
		"\2!\"\b\2\2\2\"\4\3\2\2\2#$\7v\2\2$%\7j\2\2%>\7g\2\2&\'\7q\2\2\'>\7h\2"+
		"\2()\7c\2\2)*\7p\2\2*>\7f\2\2+,\7v\2\2,>\7q\2\2->\7c\2\2./\7j\2\2/\60"+
		"\7k\2\2\60>\7u\2\2\61\62\7k\2\2\62>\7p\2\2\63\64\7y\2\2\64\65\7k\2\2\65"+
		"\66\7v\2\2\66>\7j\2\2\67>\7K\2\289\7y\2\29:\7j\2\2:;\7k\2\2;<\7e\2\2<"+
		">\7j\2\2=#\3\2\2\2=&\3\2\2\2=(\3\2\2\2=+\3\2\2\2=-\3\2\2\2=.\3\2\2\2="+
		"\61\3\2\2\2=\63\3\2\2\2=\67\3\2\2\2=8\3\2\2\2>?\3\2\2\2?@\b\3\3\2@\6\3"+
		"\2\2\2AB\13\2\2\2BC\3\2\2\2CD\b\4\4\2D\b\3\2\2\2EI\t\2\2\2FH\t\3\2\2G"+
		"F\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2\2LM\b\5\4\2"+
		"M\n\3\2\2\2\6\2\37=I\5\3\2\2\3\3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}