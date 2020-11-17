// Generated from stuff.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class stuff extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DIFFERENT=1, CLOSE=2, FAR1=3, FAR2=4, FAR3=5, FAR4=6, FAR5=7, OTHER=8, 
		REPEATED=9, SAME=10, DELAYED=11, UNAMB=12, WS=13, REST=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DIFFERENT", "CLOSE", "FAR1", "FAR2", "FAR3", "FAR4", "FAR5", "OTHER", 
			"REPEATED", "SAME", "DELAYED", "UNAMB", "WS", "REST"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DIFFERENT", "CLOSE", "FAR1", "FAR2", "FAR3", "FAR4", "FAR5", "OTHER", 
			"REPEATED", "SAME", "DELAYED", "UNAMB", "WS", "REST"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public stuff(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "stuff.g4"; }

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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20\u017f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\6\2$\n\2"+
		"\r\2\16\2%\3\2\3\2\3\2\3\2\3\2\6\2-\n\2\r\2\16\2.\5\2\61\n\2\3\3\3\3\3"+
		"\3\6\3\66\n\3\r\3\16\3\67\3\3\3\3\3\3\3\3\3\3\6\3?\n\3\r\3\16\3@\5\3C"+
		"\n\3\3\4\3\4\3\4\3\4\3\4\6\4J\n\4\r\4\16\4K\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\6\4Z\n\4\r\4\16\4[\5\4^\n\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\6\5g\n\5\r\5\16\5h\3\5\3\5\3\5\6\5n\n\5\r\5\16\5o\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6\5\u0080\n\5\r\5\16\5\u0081"+
		"\5\5\u0084\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\6\6\u008d\n\6\r\6\16\6\u008e"+
		"\3\6\3\6\3\6\6\6\u0094\n\6\r\6\16\6\u0095\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\6\6\u00a2\n\6\r\6\16\6\u00a3\3\6\3\6\3\6\5\6\u00a9\n\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\6\7\u00b2\n\7\r\7\16\7\u00b3\3\7\3\7\3\7\6"+
		"\7\u00b9\n\7\r\7\16\7\u00ba\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\6"+
		"\7\u00c7\n\7\r\7\16\7\u00c8\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\5\7\u00d9\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\6\b\u00f0\n\b\r\b\16\b\u00f1"+
		"\3\b\3\b\3\b\5\b\u00f7\n\b\3\t\3\t\3\t\3\t\3\t\3\t\6\t\u00ff\n\t\r\t\16"+
		"\t\u0100\3\t\3\t\3\t\3\t\3\t\3\t\3\t\6\t\u010a\n\t\r\t\16\t\u010b\5\t"+
		"\u010e\n\t\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u0116\n\n\r\n\16\n\u0117\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\6\n\u0121\n\n\r\n\16\n\u0122\3\n\5\n\u0126\n\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u012e\n\13\r\13\16\13\u012f\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\6\13\u0138\n\13\r\13\16\13\u0139\3\13\5\13\u013d"+
		"\n\13\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u0145\n\f\r\f\16\f\u0146\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\6\f\u0150\n\f\r\f\16\f\u0151\5\f\u0154\n\f\3\f\3\f\3"+
		"\f\5\f\u0159\n\f\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u0161\n\r\r\r\16\r\u0162"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u016c\n\r\r\r\16\r\u016d\5\r\u0170\n"+
		"\r\3\r\3\r\3\r\5\r\u0175\n\r\3\r\3\r\3\16\6\16\u017a\n\16\r\16\16\16\u017b"+
		"\3\17\3\17\34%.\67@K[ho\u0081\u008e\u0095\u00a3\u00b3\u00ba\u00c8\u00f1"+
		"\u0100\u010b\u0117\u0122\u012f\u0139\u0146\u0151\u0162\u016d\2\20\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\3\2"+
		"\4\5\2\13\f\17\17\"\"\5\2\62;C\\c|\2\u01a8\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\3\60\3\2\2\2\5B\3\2\2\2\7]\3\2\2\2\t\u0083\3\2\2\2\13\u00a8"+
		"\3\2\2\2\r\u00d8\3\2\2\2\17\u00f6\3\2\2\2\21\u010d\3\2\2\2\23\u0125\3"+
		"\2\2\2\25\u013c\3\2\2\2\27\u0153\3\2\2\2\31\u016f\3\2\2\2\33\u0179\3\2"+
		"\2\2\35\u017d\3\2\2\2\37 \7d\2\2 !\7e\2\2!#\3\2\2\2\"$\7c\2\2#\"\3\2\2"+
		"\2$%\3\2\2\2%&\3\2\2\2%#\3\2\2\2&\61\3\2\2\2\'(\7d\2\2()\7e\2\2),\3\2"+
		"\2\2*+\7c\2\2+-\7c\2\2,*\3\2\2\2-.\3\2\2\2./\3\2\2\2.,\3\2\2\2/\61\3\2"+
		"\2\2\60\37\3\2\2\2\60\'\3\2\2\2\61\4\3\2\2\2\62\65\7f\2\2\63\64\7c\2\2"+
		"\64\66\7d\2\2\65\63\3\2\2\2\66\67\3\2\2\2\678\3\2\2\2\67\65\3\2\2\28C"+
		"\3\2\2\29:\7f\2\2:;\7c\2\2;>\3\2\2\2<=\7d\2\2=?\7c\2\2><\3\2\2\2?@\3\2"+
		"\2\2@A\3\2\2\2@>\3\2\2\2AC\3\2\2\2B\62\3\2\2\2B9\3\2\2\2C\6\3\2\2\2DE"+
		"\7g\2\2EF\7d\2\2FG\7e\2\2GI\3\2\2\2HJ\7c\2\2IH\3\2\2\2JK\3\2\2\2KL\3\2"+
		"\2\2KI\3\2\2\2L^\3\2\2\2MN\7g\2\2NO\7d\2\2OP\7e\2\2PQ\7c\2\2QR\7c\2\2"+
		"RS\7c\2\2ST\7f\2\2TU\7g\2\2UV\7h\2\2VY\3\2\2\2WX\7d\2\2XZ\7e\2\2YW\3\2"+
		"\2\2Z[\3\2\2\2[\\\3\2\2\2[Y\3\2\2\2\\^\3\2\2\2]D\3\2\2\2]M\3\2\2\2^\b"+
		"\3\2\2\2_`\7k\2\2`a\7d\2\2ab\7e\2\2bf\3\2\2\2cd\7c\2\2de\7c\2\2eg\7c\2"+
		"\2fc\3\2\2\2gh\3\2\2\2hi\3\2\2\2hf\3\2\2\2im\3\2\2\2jk\7f\2\2kl\7g\2\2"+
		"ln\7h\2\2mj\3\2\2\2no\3\2\2\2op\3\2\2\2om\3\2\2\2pq\3\2\2\2q\u0084\7d"+
		"\2\2rs\7k\2\2st\7d\2\2tu\7e\2\2uv\7c\2\2vw\7c\2\2wx\7c\2\2xy\7f\2\2yz"+
		"\7g\2\2z{\7h\2\2{\177\3\2\2\2|}\7d\2\2}~\7e\2\2~\u0080\7f\2\2\177|\3\2"+
		"\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0081\177\3\2\2\2\u0082\u0084"+
		"\3\2\2\2\u0083_\3\2\2\2\u0083r\3\2\2\2\u0084\n\3\2\2\2\u0085\u0086\7j"+
		"\2\2\u0086\u0087\7d\2\2\u0087\u0088\7e\2\2\u0088\u008c\3\2\2\2\u0089\u008a"+
		"\7c\2\2\u008a\u008b\7c\2\2\u008b\u008d\7c\2\2\u008c\u0089\3\2\2\2\u008d"+
		"\u008e\3\2\2\2\u008e\u008f\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0093\3\2"+
		"\2\2\u0090\u0091\7f\2\2\u0091\u0092\7g\2\2\u0092\u0094\7h\2\2\u0093\u0090"+
		"\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096"+
		"\u0097\3\2\2\2\u0097\u00a9\7d\2\2\u0098\u0099\7j\2\2\u0099\u009a\7d\2"+
		"\2\u009a\u009b\7e\2\2\u009b\u009c\7c\2\2\u009c\u009d\7c\2\2\u009d\u009e"+
		"\7c\2\2\u009e\u009f\7f\2\2\u009f\u00a0\7g\2\2\u00a0\u00a2\7h\2\2\u00a1"+
		"\u0098\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a3\u00a1\3\2"+
		"\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\7d\2\2\u00a6\u00a7\7e\2\2\u00a7\u00a9"+
		"\7f\2\2\u00a8\u0085\3\2\2\2\u00a8\u00a1\3\2\2\2\u00a9\f\3\2\2\2\u00aa"+
		"\u00ab\7n\2\2\u00ab\u00ac\7d\2\2\u00ac\u00ad\7e\2\2\u00ad\u00b1\3\2\2"+
		"\2\u00ae\u00af\7c\2\2\u00af\u00b0\7c\2\2\u00b0\u00b2\7c\2\2\u00b1\u00ae"+
		"\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4"+
		"\u00b8\3\2\2\2\u00b5\u00b6\7f\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b9\7h\2"+
		"\2\u00b8\u00b5\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00ba\u00b8"+
		"\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00d9\7d\2\2\u00bd\u00be\7n\2\2\u00be"+
		"\u00bf\7d\2\2\u00bf\u00c0\7e\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2\7c\2\2"+
		"\u00c2\u00c3\7c\2\2\u00c3\u00c4\7f\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c7"+
		"\7h\2\2\u00c6\u00bd\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\7d\2\2\u00cb\u00cc\7e\2"+
		"\2\u00cc\u00d9\7f\2\2\u00cd\u00ce\7n\2\2\u00ce\u00cf\7d\2\2\u00cf\u00d0"+
		"\7e\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2\7c\2\2\u00d2\u00d3\7c\2\2\u00d3"+
		"\u00d4\7f\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d6\7h\2\2\u00d6\u00d7\7d\2\2"+
		"\u00d7\u00d9\7e\2\2\u00d8\u00aa\3\2\2\2\u00d8\u00c6\3\2\2\2\u00d8\u00cd"+
		"\3\2\2\2\u00d9\16\3\2\2\2\u00da\u00db\7o\2\2\u00db\u00dc\7d\2\2\u00dc"+
		"\u00dd\7e\2\2\u00dd\u00de\7c\2\2\u00de\u00df\7c\2\2\u00df\u00e0\7c\2\2"+
		"\u00e0\u00e1\3\2\2\2\u00e1\u00e2\7f\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4"+
		"\7h\2\2\u00e4\u00e5\7d\2\2\u00e5\u00f7\7e\2\2\u00e6\u00e7\7o\2\2\u00e7"+
		"\u00e8\7d\2\2\u00e8\u00e9\7e\2\2\u00e9\u00ea\7c\2\2\u00ea\u00eb\7c\2\2"+
		"\u00eb\u00ec\7c\2\2\u00ec\u00ed\7f\2\2\u00ed\u00ee\7g\2\2\u00ee\u00f0"+
		"\7h\2\2\u00ef\u00e6\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f1"+
		"\u00ef\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\7d\2\2\u00f4\u00f5\7e\2"+
		"\2\u00f5\u00f7\7f\2\2\u00f6\u00da\3\2\2\2\u00f6\u00ef\3\2\2\2\u00f7\20"+
		"\3\2\2\2\u00f8\u00fe\7l\2\2\u00f9\u00fa\7d\2\2\u00fa\u00fb\7e\2\2\u00fb"+
		"\u00fc\7d\2\2\u00fc\u00fd\7e\2\2\u00fd\u00ff\7d\2\2\u00fe\u00f9\3\2\2"+
		"\2\u00ff\u0100\3\2\2\2\u0100\u0101\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u010e"+
		"\3\2\2\2\u0102\u0109\7l\2\2\u0103\u0104\7d\2\2\u0104\u0105\7e\2\2\u0105"+
		"\u0106\7d\2\2\u0106\u0107\7e\2\2\u0107\u0108\7d\2\2\u0108\u010a\7c\2\2"+
		"\u0109\u0103\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010c\3\2\2\2\u010b\u0109"+
		"\3\2\2\2\u010c\u010e\3\2\2\2\u010d\u00f8\3\2\2\2\u010d\u0102\3\2\2\2\u010e"+
		"\22\3\2\2\2\u010f\u0115\7h\2\2\u0110\u0111\7d\2\2\u0111\u0112\7e\2\2\u0112"+
		"\u0113\7d\2\2\u0113\u0114\7e\2\2\u0114\u0116\7d\2\2\u0115\u0110\3\2\2"+
		"\2\u0116\u0117\3\2\2\2\u0117\u0118\3\2\2\2\u0117\u0115\3\2\2\2\u0118\u0126"+
		"\3\2\2\2\u0119\u011a\7h\2\2\u011a\u011b\7d\2\2\u011b\u011c\7e\2\2\u011c"+
		"\u0120\3\2\2\2\u011d\u011e\7d\2\2\u011e\u011f\7e\2\2\u011f\u0121\7d\2"+
		"\2\u0120\u011d\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\3\2\2\2\u0122\u0120"+
		"\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0126\7c\2\2\u0125\u010f\3\2\2\2\u0125"+
		"\u0119\3\2\2\2\u0126\24\3\2\2\2\u0127\u012d\7i\2\2\u0128\u0129\7d\2\2"+
		"\u0129\u012a\7e\2\2\u012a\u012b\7d\2\2\u012b\u012c\7e\2\2\u012c\u012e"+
		"\7d\2\2\u012d\u0128\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\3\2\2\2\u012f"+
		"\u012d\3\2\2\2\u0130\u013d\3\2\2\2\u0131\u0137\7i\2\2\u0132\u0133\7d\2"+
		"\2\u0133\u0134\7e\2\2\u0134\u0135\7d\2\2\u0135\u0136\7e\2\2\u0136\u0138"+
		"\7d\2\2\u0137\u0132\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013a\3\2\2\2\u0139"+
		"\u0137\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013d\7c\2\2\u013c\u0127\3\2"+
		"\2\2\u013c\u0131\3\2\2\2\u013d\26\3\2\2\2\u013e\u0144\7c\2\2\u013f\u0140"+
		"\7d\2\2\u0140\u0141\7e\2\2\u0141\u0142\7d\2\2\u0142\u0143\7e\2\2\u0143"+
		"\u0145\7d\2\2\u0144\u013f\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\3\2"+
		"\2\2\u0146\u0144\3\2\2\2\u0147\u0154\3\2\2\2\u0148\u0149\7c\2\2\u0149"+
		"\u014a\7d\2\2\u014a\u014b\7e\2\2\u014b\u014f\3\2\2\2\u014c\u014d\7d\2"+
		"\2\u014d\u014e\7e\2\2\u014e\u0150\7d\2\2\u014f\u014c\3\2\2\2\u0150\u0151"+
		"\3\2\2\2\u0151\u0152\3\2\2\2\u0151\u014f\3\2\2\2\u0152\u0154\3\2\2\2\u0153"+
		"\u013e\3\2\2\2\u0153\u0148\3\2\2\2\u0154\u0158\3\2\2\2\u0155\u0159\7c"+
		"\2\2\u0156\u0157\7c\2\2\u0157\u0159\7d\2\2\u0158\u0155\3\2\2\2\u0158\u0156"+
		"\3\2\2\2\u0159\30\3\2\2\2\u015a\u0160\7m\2\2\u015b\u015c\7d\2\2\u015c"+
		"\u015d\7e\2\2\u015d\u015e\7d\2\2\u015e\u015f\7e\2\2\u015f\u0161\7d\2\2"+
		"\u0160\u015b\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0163\3\2\2\2\u0162\u0160"+
		"\3\2\2\2\u0163\u0170\3\2\2\2\u0164\u0165\7m\2\2\u0165\u0166\7d\2\2\u0166"+
		"\u0167\7e\2\2\u0167\u016b\3\2\2\2\u0168\u0169\7d\2\2\u0169\u016a\7e\2"+
		"\2\u016a\u016c\7d\2\2\u016b\u0168\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016e"+
		"\3\2\2\2\u016d\u016b\3\2\2\2\u016e\u0170\3\2\2\2\u016f\u015a\3\2\2\2\u016f"+
		"\u0164\3\2\2\2\u0170\u0174\3\2\2\2\u0171\u0175\7c\2\2\u0172\u0173\7c\2"+
		"\2\u0173\u0175\7d\2\2\u0174\u0171\3\2\2\2\u0174\u0172\3\2\2\2\u0175\u0176"+
		"\3\2\2\2\u0176\u0177\7m\2\2\u0177\32\3\2\2\2\u0178\u017a\t\2\2\2\u0179"+
		"\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017c\3\2"+
		"\2\2\u017c\34\3\2\2\2\u017d\u017e\t\3\2\2\u017e\36\3\2\2\2,\2%.\60\67"+
		"@BK[]ho\u0081\u0083\u008e\u0095\u00a3\u00a8\u00b3\u00ba\u00c8\u00d8\u00f1"+
		"\u00f6\u0100\u010b\u010d\u0117\u0122\u0125\u012f\u0139\u013c\u0146\u0151"+
		"\u0153\u0158\u0162\u016d\u016f\u0174\u017b\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}