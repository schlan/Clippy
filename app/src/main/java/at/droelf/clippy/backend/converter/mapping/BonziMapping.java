package at.droelf.clippy.backend.converter.mapping;

import at.droelf.clippy.R;

public class BonziMapping implements AgentMapping{

    private final static int NUMBER_COLUMNS = 17;
    private final static int NUMBER_ROWS = 21;

    private static final int [] IMAGE_MAPPING = {
            R.drawable.bonzi_0000,
            R.drawable.bonzi_0001,
            R.drawable.bonzi_0002,
            R.drawable.bonzi_0003,
            R.drawable.bonzi_0004,
            R.drawable.bonzi_0005,
            R.drawable.bonzi_0006,
            R.drawable.bonzi_0007,
            R.drawable.bonzi_0008,
            R.drawable.bonzi_0009,
            R.drawable.bonzi_0010,
            R.drawable.bonzi_0011,
            R.drawable.bonzi_0012,
            R.drawable.bonzi_0013,
            R.drawable.bonzi_0014,
            R.drawable.bonzi_0015,
            R.drawable.bonzi_0016,
            R.drawable.bonzi_0017,
            R.drawable.bonzi_0018,
            R.drawable.bonzi_0019,
            R.drawable.bonzi_0020,
            R.drawable.bonzi_0021,
            R.drawable.bonzi_0022,
            R.drawable.bonzi_0023,
            R.drawable.bonzi_0024,
            R.drawable.bonzi_0025,
            R.drawable.bonzi_0026,
            R.drawable.bonzi_0027,
            R.drawable.bonzi_0028,
            R.drawable.bonzi_0029,
            R.drawable.bonzi_0030,
            R.drawable.bonzi_0031,
            R.drawable.bonzi_0032,
            R.drawable.bonzi_0033,
            R.drawable.bonzi_0034,
            R.drawable.bonzi_0035,
            R.drawable.bonzi_0036,
            R.drawable.bonzi_0037,
            R.drawable.bonzi_0038,
            R.drawable.bonzi_0039,
            R.drawable.bonzi_0040,
            R.drawable.bonzi_0041,
            R.drawable.bonzi_0042,
            R.drawable.bonzi_0043,
            R.drawable.bonzi_0044,
            R.drawable.bonzi_0045,
            R.drawable.bonzi_0046,
            R.drawable.bonzi_0047,
            R.drawable.bonzi_0048,
            R.drawable.bonzi_0049,
            R.drawable.bonzi_0050,
            R.drawable.bonzi_0051,
            R.drawable.bonzi_0052,
            R.drawable.bonzi_0053,
            R.drawable.bonzi_0054,
            R.drawable.bonzi_0055,
            R.drawable.bonzi_0056,
            R.drawable.bonzi_0057,
            R.drawable.bonzi_0058,
            R.drawable.bonzi_0059,
            R.drawable.bonzi_0060,
            R.drawable.bonzi_0061,
            R.drawable.bonzi_0062,
            R.drawable.bonzi_0063,
            R.drawable.bonzi_0064,
            R.drawable.bonzi_0065,
            R.drawable.bonzi_0066,
            R.drawable.bonzi_0067,
            R.drawable.bonzi_0068,
            R.drawable.bonzi_0069,
            R.drawable.bonzi_0070,
            R.drawable.bonzi_0071,
            R.drawable.bonzi_0072,
            R.drawable.bonzi_0073,
            R.drawable.bonzi_0074,
            R.drawable.bonzi_0075,
            R.drawable.bonzi_0076,
            R.drawable.bonzi_0077,
            R.drawable.bonzi_0078,
            R.drawable.bonzi_0079,
            R.drawable.bonzi_0080,
            R.drawable.bonzi_0081,
            R.drawable.bonzi_0082,
            R.drawable.bonzi_0083,
            R.drawable.bonzi_0084,
            R.drawable.bonzi_0085,
            R.drawable.bonzi_0086,
            R.drawable.bonzi_0087,
            R.drawable.bonzi_0088,
            R.drawable.bonzi_0089,
            R.drawable.bonzi_0090,
            R.drawable.bonzi_0091,
            R.drawable.bonzi_0092,
            R.drawable.bonzi_0093,
            R.drawable.bonzi_0094,
            R.drawable.bonzi_0095,
            R.drawable.bonzi_0096,
            R.drawable.bonzi_0097,
            R.drawable.bonzi_0098,
            R.drawable.bonzi_0099,
            R.drawable.bonzi_0100,
            R.drawable.bonzi_0101,
            R.drawable.bonzi_0102,
            R.drawable.bonzi_0103,
            R.drawable.bonzi_0104,
            R.drawable.bonzi_0105,
            R.drawable.bonzi_0106,
            R.drawable.bonzi_0107,
            R.drawable.bonzi_0108,
            R.drawable.bonzi_0109,
            R.drawable.bonzi_0110,
            R.drawable.bonzi_0111,
            R.drawable.bonzi_0112,
            R.drawable.bonzi_0113,
            R.drawable.bonzi_0114,
            R.drawable.bonzi_0115,
            R.drawable.bonzi_0116,
            R.drawable.bonzi_0117,
            R.drawable.bonzi_0118,
            R.drawable.bonzi_0119,
            R.drawable.bonzi_0120,
            R.drawable.bonzi_0121,
            R.drawable.bonzi_0122,
            R.drawable.bonzi_0123,
            R.drawable.bonzi_0124,
            R.drawable.bonzi_0125,
            R.drawable.bonzi_0126,
            R.drawable.bonzi_0127,
            R.drawable.bonzi_0128,
            R.drawable.bonzi_0129,
            R.drawable.bonzi_0130,
            R.drawable.bonzi_0131,
            R.drawable.bonzi_0132,
            R.drawable.bonzi_0133,
            R.drawable.bonzi_0134,
            R.drawable.bonzi_0135,
            R.drawable.bonzi_0136,
            R.drawable.bonzi_0137,
            R.drawable.bonzi_0138,
            R.drawable.bonzi_0139,
            R.drawable.bonzi_0140,
            R.drawable.bonzi_0141,
            R.drawable.bonzi_0142,
            R.drawable.bonzi_0143,
            R.drawable.bonzi_0144,
            R.drawable.bonzi_0145,
            R.drawable.bonzi_0146,
            R.drawable.bonzi_0147,
            R.drawable.bonzi_0148,
            R.drawable.bonzi_0149,
            R.drawable.bonzi_0150,
            R.drawable.bonzi_0151,
            R.drawable.bonzi_0152,
            R.drawable.bonzi_0153,
            R.drawable.bonzi_0154,
            R.drawable.bonzi_0155,
            R.drawable.bonzi_0156,
            R.drawable.bonzi_0157,
            R.drawable.bonzi_0158,
            R.drawable.bonzi_0159,
            R.drawable.bonzi_0160,
            R.drawable.bonzi_0161,
            R.drawable.bonzi_0162,
            R.drawable.bonzi_0163,
            R.drawable.bonzi_0164,
            R.drawable.bonzi_0165,
            R.drawable.bonzi_0166,
            R.drawable.bonzi_0167,
            R.drawable.bonzi_0168,
            R.drawable.bonzi_0169,
            R.drawable.bonzi_0170,
            R.drawable.bonzi_0171,
            R.drawable.bonzi_0172,
            R.drawable.bonzi_0173,
            R.drawable.bonzi_0174,
            R.drawable.bonzi_0175,
            R.drawable.bonzi_0176,
            R.drawable.bonzi_0177,
            R.drawable.bonzi_0178,
            R.drawable.bonzi_0179,
            R.drawable.bonzi_0180,
            R.drawable.bonzi_0181,
            R.drawable.bonzi_0182,
            R.drawable.bonzi_0183,
            R.drawable.bonzi_0184,
            R.drawable.bonzi_0185,
            R.drawable.bonzi_0186,
            R.drawable.bonzi_0187,
            R.drawable.bonzi_0188,
            R.drawable.bonzi_0189,
            R.drawable.bonzi_0190,
            R.drawable.bonzi_0191,
            R.drawable.bonzi_0192,
            R.drawable.bonzi_0193,
            R.drawable.bonzi_0194,
            R.drawable.bonzi_0195,
            R.drawable.bonzi_0196,
            R.drawable.bonzi_0197,
            R.drawable.bonzi_0198,
            R.drawable.bonzi_0199,
            R.drawable.bonzi_0200,
            R.drawable.bonzi_0201,
            R.drawable.bonzi_0202,
            R.drawable.bonzi_0203,
            R.drawable.bonzi_0204,
            R.drawable.bonzi_0205,
            R.drawable.bonzi_0206,
            R.drawable.bonzi_0207,
            R.drawable.bonzi_0208,
            R.drawable.bonzi_0209,
            R.drawable.bonzi_0210,
            R.drawable.bonzi_0211,
            R.drawable.bonzi_0212,
            R.drawable.bonzi_0213,
            R.drawable.bonzi_0214,
            R.drawable.bonzi_0215,
            R.drawable.bonzi_0216,
            R.drawable.bonzi_0217,
            R.drawable.bonzi_0218,
            R.drawable.bonzi_0219,
            R.drawable.bonzi_0220,
            R.drawable.bonzi_0221,
            R.drawable.bonzi_0222,
            R.drawable.bonzi_0223,
            R.drawable.bonzi_0224,
            R.drawable.bonzi_0225,
            R.drawable.bonzi_0226,
            R.drawable.bonzi_0227,
            R.drawable.bonzi_0228,
            R.drawable.bonzi_0229,
            R.drawable.bonzi_0230,
            R.drawable.bonzi_0231,
            R.drawable.bonzi_0232,
            R.drawable.bonzi_0233,
            R.drawable.bonzi_0234,
            R.drawable.bonzi_0235,
            R.drawable.bonzi_0236,
            R.drawable.bonzi_0237,
            R.drawable.bonzi_0238,
            R.drawable.bonzi_0239,
            R.drawable.bonzi_0240,
            R.drawable.bonzi_0241,
            R.drawable.bonzi_0242,
            R.drawable.bonzi_0243,
            R.drawable.bonzi_0244,
            R.drawable.bonzi_0245,
            R.drawable.bonzi_0246,
            R.drawable.bonzi_0247,
            R.drawable.bonzi_0248,
            R.drawable.bonzi_0249,
            R.drawable.bonzi_0250,
            R.drawable.bonzi_0251,
            R.drawable.bonzi_0252,
            R.drawable.bonzi_0253,
            R.drawable.bonzi_0254,
            R.drawable.bonzi_0255,
            R.drawable.bonzi_0256,
            R.drawable.bonzi_0257,
            R.drawable.bonzi_0258,
            R.drawable.bonzi_0259,
            R.drawable.bonzi_0260,
            R.drawable.bonzi_0261,
            R.drawable.bonzi_0262,
            R.drawable.bonzi_0263,
            R.drawable.bonzi_0264,
            R.drawable.bonzi_0265,
            R.drawable.bonzi_0266,
            R.drawable.bonzi_0267,
            R.drawable.bonzi_0268,
            R.drawable.bonzi_0269,
            R.drawable.bonzi_0270,
            R.drawable.bonzi_0271,
            R.drawable.bonzi_0272,
            R.drawable.bonzi_0273,
            R.drawable.bonzi_0274,
            R.drawable.bonzi_0275,
            R.drawable.bonzi_0276,
            R.drawable.bonzi_0277,
            R.drawable.bonzi_0278,
            R.drawable.bonzi_0279,
            R.drawable.bonzi_0280,
            R.drawable.bonzi_0281,
            R.drawable.bonzi_0282,
            R.drawable.bonzi_0283,
            R.drawable.bonzi_0284,
            R.drawable.bonzi_0285,
            R.drawable.bonzi_0286,
            R.drawable.bonzi_0287,
            R.drawable.bonzi_0288,
            R.drawable.bonzi_0289,
            R.drawable.bonzi_0290,
            R.drawable.bonzi_0291,
            R.drawable.bonzi_0292,
            R.drawable.bonzi_0293,
            R.drawable.bonzi_0294,
            R.drawable.bonzi_0295,
            R.drawable.bonzi_0296,
            R.drawable.bonzi_0297,
            R.drawable.bonzi_0298,
            R.drawable.bonzi_0299,
            R.drawable.bonzi_0300,
            R.drawable.bonzi_0301,
            R.drawable.bonzi_0302,
            R.drawable.bonzi_0303,
            R.drawable.bonzi_0304,
            R.drawable.bonzi_0305,
            R.drawable.bonzi_0306,
            R.drawable.bonzi_0307,
            R.drawable.bonzi_0308,
            R.drawable.bonzi_0309,
            R.drawable.bonzi_0310,
            R.drawable.bonzi_0311,
            R.drawable.bonzi_0312,
            R.drawable.bonzi_0313,
            R.drawable.bonzi_0314,
            R.drawable.bonzi_0315,
            R.drawable.bonzi_0316,
            R.drawable.bonzi_0317,
            R.drawable.bonzi_0318,
            R.drawable.bonzi_0319,
            R.drawable.bonzi_0320,
            R.drawable.bonzi_0321,
            R.drawable.bonzi_0322,
            R.drawable.bonzi_0323,
            R.drawable.bonzi_0324,
            R.drawable.bonzi_0325,
            R.drawable.bonzi_0326,
            R.drawable.bonzi_0327,
            R.drawable.bonzi_0328,
            R.drawable.bonzi_0329,
            R.drawable.bonzi_0330,
            R.drawable.bonzi_0331,
            R.drawable.bonzi_0332,
            R.drawable.bonzi_0333,
            R.drawable.bonzi_0334,
            R.drawable.bonzi_0335,
            R.drawable.bonzi_0336,
            R.drawable.bonzi_0337,
            R.drawable.bonzi_0338,
            R.drawable.bonzi_0339,
            R.drawable.bonzi_0340,
            R.drawable.bonzi_0341,
            R.drawable.bonzi_0342,
            R.drawable.bonzi_0343,
            R.drawable.bonzi_0344,
            R.drawable.bonzi_0345,
            R.drawable.bonzi_0346,
            R.drawable.bonzi_0347,
            R.drawable.bonzi_0348,
            R.drawable.bonzi_0349,
            R.drawable.bonzi_0350,
            R.drawable.bonzi_0351,
            R.drawable.bonzi_0352,
            R.drawable.bonzi_0353,
            R.drawable.bonzi_0354,
            R.drawable.bonzi_0355,
            R.drawable.bonzi_0356
    };

    private static final int[] SOUND_MAPPING = {
            R.raw.bonzi_snd_01,
            R.raw.bonzi_snd_02,
            R.raw.bonzi_snd_03,
            R.raw.bonzi_snd_04,
            R.raw.bonzi_snd_05,
            R.raw.bonzi_snd_06,
            R.raw.bonzi_snd_07
    };

    @Override
    public int[] getMapping() {
        return IMAGE_MAPPING;
    }

    @Override
    public int[] getSoundMapping() {
        return SOUND_MAPPING;
    }

    @Override
    public int getNumberRows() {
        return NUMBER_ROWS;
    }

    @Override
    public int getNumberColumns() {
        return NUMBER_COLUMNS;
    }

    @Override
    public int getEmptyFrameId() {
        return R.drawable.bonzi_0356;
    }

    @Override
    public int getFirstFrameId() {
        return R.drawable.bonzi_0000;
    }
}
