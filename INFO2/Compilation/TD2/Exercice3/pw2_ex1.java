// Generated from pw2_ex1.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class pw2_ex1 extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TIME=1, OTHER=2, WS=3, EOL=4;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"TIME", "OTHER", "WS", "EOL"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "TIME", "OTHER", "WS", "EOL"
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


	    static int count =1;


	public pw2_ex1(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "pw2_ex1.g4"; }

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
			TIME_action((RuleContext)_localctx, actionIndex);
			break;
		case 3:
			EOL_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void TIME_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 System.out.println("line "+ count + ", " + getText());
			break;
		}
	}
	private void EOL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			count++;
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\68\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\5\2\35\n\2\3\3\6\3 \n\3\r\3\16\3!\3\3\6\3%\n\3\r"+
		"\3\16\3&\5\3)\n\3\3\3\3\3\3\4\6\4.\n\4\r\4\16\4/\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\2\2\6\3\3\5\4\7\5\t\6\3\2\n\3\2\62\62\3\2\65\65\3\2\62;\3\2\66"+
		"\66\6\2\13\f\17\17\"\"\62;\6\2\13\f\17\17\"\"<<\5\2\13\13\17\17\"\"\3"+
		"\2\f\f\2<\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\3\34\3\2\2\2"+
		"\5(\3\2\2\2\7-\3\2\2\2\t\63\3\2\2\2\13\f\t\2\2\2\f\r\t\3\2\2\r\16\7<\2"+
		"\2\16\17\t\4\2\2\17\20\t\4\2\2\20\21\7<\2\2\21\22\t\4\2\2\22\35\t\4\2"+
		"\2\23\24\t\2\2\2\24\25\t\5\2\2\25\26\7<\2\2\26\27\t\2\2\2\27\30\t\2\2"+
		"\2\30\31\7<\2\2\31\32\t\2\2\2\32\33\t\2\2\2\33\35\b\2\2\2\34\13\3\2\2"+
		"\2\34\23\3\2\2\2\35\4\3\2\2\2\36 \n\6\2\2\37\36\3\2\2\2 !\3\2\2\2!\37"+
		"\3\2\2\2!\"\3\2\2\2\")\3\2\2\2#%\n\7\2\2$#\3\2\2\2%&\3\2\2\2&$\3\2\2\2"+
		"&\'\3\2\2\2\')\3\2\2\2(\37\3\2\2\2($\3\2\2\2)*\3\2\2\2*+\b\3\3\2+\6\3"+
		"\2\2\2,.\t\b\2\2-,\3\2\2\2./\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\61\3\2\2"+
		"\2\61\62\b\4\3\2\62\b\3\2\2\2\63\64\t\t\2\2\64\65\b\5\4\2\65\66\3\2\2"+
		"\2\66\67\b\5\3\2\67\n\3\2\2\2\b\2\34!&(/\5\3\2\2\b\2\2\3\5\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}