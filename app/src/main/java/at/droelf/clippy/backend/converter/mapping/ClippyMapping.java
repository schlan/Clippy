package at.droelf.clippy.backend.converter.mapping;


import at.droelf.clippy.R;

public class ClippyMapping implements AgentMapping {

    private final int numberColumns = 27;
    private final int numberRows = 34;

    private final int[] mapping = {
            R.drawable.clippy_0000,
            R.drawable.clippy_0001,
            R.drawable.clippy_0002,
            R.drawable.clippy_0003,
            R.drawable.clippy_0004,
            R.drawable.clippy_0005,
            R.drawable.clippy_0006,
            R.drawable.clippy_0007,
            R.drawable.clippy_0008,
            R.drawable.clippy_0009,
            R.drawable.clippy_0010,
            R.drawable.clippy_0011,
            R.drawable.clippy_0012,
            R.drawable.clippy_0013,
            R.drawable.clippy_0014,
            R.drawable.clippy_0015,
            R.drawable.clippy_0016,
            R.drawable.clippy_0017,
            R.drawable.clippy_0018,
            R.drawable.clippy_0019,
            R.drawable.clippy_0020,
            R.drawable.clippy_0021,
            R.drawable.clippy_0022,
            R.drawable.clippy_0023,
            R.drawable.clippy_0024,
            R.drawable.clippy_0025,
            R.drawable.clippy_0026,
            R.drawable.clippy_0027,
            R.drawable.clippy_0028,
            R.drawable.clippy_0029,
            R.drawable.clippy_0030,
            R.drawable.clippy_0031,
            R.drawable.clippy_0032,
            R.drawable.clippy_0033,
            R.drawable.clippy_0034,
            R.drawable.clippy_0035,
            R.drawable.clippy_0036,
            R.drawable.clippy_0037,
            R.drawable.clippy_0038,
            R.drawable.clippy_0039,
            R.drawable.clippy_0040,
            R.drawable.clippy_0041,
            R.drawable.clippy_0042,
            R.drawable.clippy_0043,
            R.drawable.clippy_0044,
            R.drawable.clippy_0045,
            R.drawable.clippy_0046,
            R.drawable.clippy_0047,
            R.drawable.clippy_0048,
            R.drawable.clippy_0049,
            R.drawable.clippy_0050,
            R.drawable.clippy_0051,
            R.drawable.clippy_0052,
            R.drawable.clippy_0053,
            R.drawable.clippy_0054,
            R.drawable.clippy_0055,
            R.drawable.clippy_0056,
            R.drawable.clippy_0057,
            R.drawable.clippy_0058,
            R.drawable.clippy_0059,
            R.drawable.clippy_0060,
            R.drawable.clippy_0061,
            R.drawable.clippy_0062,
            R.drawable.clippy_0063,
            R.drawable.clippy_0064,
            R.drawable.clippy_0065,
            R.drawable.clippy_0066,
            R.drawable.clippy_0067,
            R.drawable.clippy_0068,
            R.drawable.clippy_0069,
            R.drawable.clippy_0070,
            R.drawable.clippy_0071,
            R.drawable.clippy_0072,
            R.drawable.clippy_0073,
            R.drawable.clippy_0074,
            R.drawable.clippy_0075,
            R.drawable.clippy_0076,
            R.drawable.clippy_0077,
            R.drawable.clippy_0078,
            R.drawable.clippy_0079,
            R.drawable.clippy_0080,
            R.drawable.clippy_0081,
            R.drawable.clippy_0082,
            R.drawable.clippy_0083,
            R.drawable.clippy_0084,
            R.drawable.clippy_0085,
            R.drawable.clippy_0086,
            R.drawable.clippy_0087,
            R.drawable.clippy_0088,
            R.drawable.clippy_0089,
            R.drawable.clippy_0090,
            R.drawable.clippy_0091,
            R.drawable.clippy_0092,
            R.drawable.clippy_0093,
            R.drawable.clippy_0094,
            R.drawable.clippy_0095,
            R.drawable.clippy_0096,
            R.drawable.clippy_0097,
            R.drawable.clippy_0098,
            R.drawable.clippy_0099,
            R.drawable.clippy_0100,
            R.drawable.clippy_0101,
            R.drawable.clippy_0102,
            R.drawable.clippy_0103,
            R.drawable.clippy_0104,
            R.drawable.clippy_0105,
            R.drawable.clippy_0106,
            R.drawable.clippy_0107,
            R.drawable.clippy_0108,
            R.drawable.clippy_0109,
            R.drawable.clippy_0110,
            R.drawable.clippy_0111,
            R.drawable.clippy_0112,
            R.drawable.clippy_0113,
            R.drawable.clippy_0114,
            R.drawable.clippy_0115,
            R.drawable.clippy_0116,
            R.drawable.clippy_0117,
            R.drawable.clippy_0118,
            R.drawable.clippy_0119,
            R.drawable.clippy_0120,
            R.drawable.clippy_0121,
            R.drawable.clippy_0122,
            R.drawable.clippy_0123,
            R.drawable.clippy_0124,
            R.drawable.clippy_0125,
            R.drawable.clippy_0126,
            R.drawable.clippy_0127,
            R.drawable.clippy_0128,
            R.drawable.clippy_0129,
            R.drawable.clippy_0130,
            R.drawable.clippy_0131,
            R.drawable.clippy_0132,
            R.drawable.clippy_0133,
            R.drawable.clippy_0134,
            R.drawable.clippy_0135,
            R.drawable.clippy_0136,
            R.drawable.clippy_0137,
            R.drawable.clippy_0138,
            R.drawable.clippy_0139,
            R.drawable.clippy_0140,
            R.drawable.clippy_0141,
            R.drawable.clippy_0142,
            R.drawable.clippy_0143,
            R.drawable.clippy_0144,
            R.drawable.clippy_0145,
            R.drawable.clippy_0146,
            R.drawable.clippy_0147,
            R.drawable.clippy_0148,
            R.drawable.clippy_0149,
            R.drawable.clippy_0150,
            R.drawable.clippy_0151,
            R.drawable.clippy_0152,
            R.drawable.clippy_0153,
            R.drawable.clippy_0154,
            R.drawable.clippy_0155,
            R.drawable.clippy_0156,
            R.drawable.clippy_0157,
            R.drawable.clippy_0158,
            R.drawable.clippy_0159,
            R.drawable.clippy_0160,
            R.drawable.clippy_0161,
            R.drawable.clippy_0162,
            R.drawable.clippy_0163,
            R.drawable.clippy_0164,
            R.drawable.clippy_0165,
            R.drawable.clippy_0166,
            R.drawable.clippy_0167,
            R.drawable.clippy_0168,
            R.drawable.clippy_0169,
            R.drawable.clippy_0170,
            R.drawable.clippy_0171,
            R.drawable.clippy_0172,
            R.drawable.clippy_0173,
            R.drawable.clippy_0174,
            R.drawable.clippy_0175,
            R.drawable.clippy_0176,
            R.drawable.clippy_0177,
            R.drawable.clippy_0178,
            R.drawable.clippy_0179,
            R.drawable.clippy_0180,
            R.drawable.clippy_0181,
            R.drawable.clippy_0182,
            R.drawable.clippy_0183,
            R.drawable.clippy_0184,
            R.drawable.clippy_0185,
            R.drawable.clippy_0186,
            R.drawable.clippy_0187,
            R.drawable.clippy_0188,
            R.drawable.clippy_0189,
            R.drawable.clippy_0190,
            R.drawable.clippy_0191,
            R.drawable.clippy_0192,
            R.drawable.clippy_0193,
            R.drawable.clippy_0194,
            R.drawable.clippy_0195,
            R.drawable.clippy_0196,
            R.drawable.clippy_0197,
            R.drawable.clippy_0198,
            R.drawable.clippy_0199,
            R.drawable.clippy_0200,
            R.drawable.clippy_0201,
            R.drawable.clippy_0202,
            R.drawable.clippy_0203,
            R.drawable.clippy_0204,
            R.drawable.clippy_0205,
            R.drawable.clippy_0206,
            R.drawable.clippy_0207,
            R.drawable.clippy_0208,
            R.drawable.clippy_0209,
            R.drawable.clippy_0210,
            R.drawable.clippy_0211,
            R.drawable.clippy_0212,
            R.drawable.clippy_0213,
            R.drawable.clippy_0214,
            R.drawable.clippy_0215,
            R.drawable.clippy_0216,
            R.drawable.clippy_0217,
            R.drawable.clippy_0218,
            R.drawable.clippy_0219,
            R.drawable.clippy_0220,
            R.drawable.clippy_0221,
            R.drawable.clippy_0222,
            R.drawable.clippy_0223,
            R.drawable.clippy_0224,
            R.drawable.clippy_0225,
            R.drawable.clippy_0226,
            R.drawable.clippy_0227,
            R.drawable.clippy_0228,
            R.drawable.clippy_0229,
            R.drawable.clippy_0230,
            R.drawable.clippy_0231,
            R.drawable.clippy_0232,
            R.drawable.clippy_0233,
            R.drawable.clippy_0234,
            R.drawable.clippy_0235,
            R.drawable.clippy_0236,
            R.drawable.clippy_0237,
            R.drawable.clippy_0238,
            R.drawable.clippy_0239,
            R.drawable.clippy_0240,
            R.drawable.clippy_0241,
            R.drawable.clippy_0242,
            R.drawable.clippy_0243,
            R.drawable.clippy_0244,
            R.drawable.clippy_0245,
            R.drawable.clippy_0246,
            R.drawable.clippy_0247,
            R.drawable.clippy_0248,
            R.drawable.clippy_0249,
            R.drawable.clippy_0250,
            R.drawable.clippy_0251,
            R.drawable.clippy_0252,
            R.drawable.clippy_0253,
            R.drawable.clippy_0254,
            R.drawable.clippy_0255,
            R.drawable.clippy_0256,
            R.drawable.clippy_0257,
            R.drawable.clippy_0258,
            R.drawable.clippy_0259,
            R.drawable.clippy_0260,
            R.drawable.clippy_0261,
            R.drawable.clippy_0262,
            R.drawable.clippy_0263,
            R.drawable.clippy_0264,
            R.drawable.clippy_0265,
            R.drawable.clippy_0266,
            R.drawable.clippy_0267,
            R.drawable.clippy_0268,
            R.drawable.clippy_0269,
            R.drawable.clippy_0270,
            R.drawable.clippy_0271,
            R.drawable.clippy_0272,
            R.drawable.clippy_0273,
            R.drawable.clippy_0274,
            R.drawable.clippy_0275,
            R.drawable.clippy_0276,
            R.drawable.clippy_0277,
            R.drawable.clippy_0278,
            R.drawable.clippy_0279,
            R.drawable.clippy_0280,
            R.drawable.clippy_0281,
            R.drawable.clippy_0282,
            R.drawable.clippy_0283,
            R.drawable.clippy_0284,
            R.drawable.clippy_0285,
            R.drawable.clippy_0286,
            R.drawable.clippy_0287,
            R.drawable.clippy_0288,
            R.drawable.clippy_0289,
            R.drawable.clippy_0290,
            R.drawable.clippy_0291,
            R.drawable.clippy_0292,
            R.drawable.clippy_0293,
            R.drawable.clippy_0294,
            R.drawable.clippy_0295,
            R.drawable.clippy_0296,
            R.drawable.clippy_0297,
            R.drawable.clippy_0298,
            R.drawable.clippy_0299,
            R.drawable.clippy_0300,
            R.drawable.clippy_0301,
            R.drawable.clippy_0302,
            R.drawable.clippy_0303,
            R.drawable.clippy_0304,
            R.drawable.clippy_0305,
            R.drawable.clippy_0306,
            R.drawable.clippy_0307,
            R.drawable.clippy_0308,
            R.drawable.clippy_0309,
            R.drawable.clippy_0310,
            R.drawable.clippy_0311,
            R.drawable.clippy_0312,
            R.drawable.clippy_0313,
            R.drawable.clippy_0314,
            R.drawable.clippy_0315,
            R.drawable.clippy_0316,
            R.drawable.clippy_0317,
            R.drawable.clippy_0318,
            R.drawable.clippy_0319,
            R.drawable.clippy_0320,
            R.drawable.clippy_0321,
            R.drawable.clippy_0322,
            R.drawable.clippy_0323,
            R.drawable.clippy_0324,
            R.drawable.clippy_0325,
            R.drawable.clippy_0326,
            R.drawable.clippy_0327,
            R.drawable.clippy_0328,
            R.drawable.clippy_0329,
            R.drawable.clippy_0330,
            R.drawable.clippy_0331,
            R.drawable.clippy_0332,
            R.drawable.clippy_0333,
            R.drawable.clippy_0334,
            R.drawable.clippy_0335,
            R.drawable.clippy_0336,
            R.drawable.clippy_0337,
            R.drawable.clippy_0338,
            R.drawable.clippy_0339,
            R.drawable.clippy_0340,
            R.drawable.clippy_0341,
            R.drawable.clippy_0342,
            R.drawable.clippy_0343,
            R.drawable.clippy_0344,
            R.drawable.clippy_0345,
            R.drawable.clippy_0346,
            R.drawable.clippy_0347,
            R.drawable.clippy_0348,
            R.drawable.clippy_0349,
            R.drawable.clippy_0350,
            R.drawable.clippy_0351,
            R.drawable.clippy_0352,
            R.drawable.clippy_0353,
            R.drawable.clippy_0354,
            R.drawable.clippy_0355,
            R.drawable.clippy_0356,
            R.drawable.clippy_0357,
            R.drawable.clippy_0358,
            R.drawable.clippy_0359,
            R.drawable.clippy_0360,
            R.drawable.clippy_0361,
            R.drawable.clippy_0362,
            R.drawable.clippy_0363,
            R.drawable.clippy_0364,
            R.drawable.clippy_0365,
            R.drawable.clippy_0366,
            R.drawable.clippy_0367,
            R.drawable.clippy_0368,
            R.drawable.clippy_0369,
            R.drawable.clippy_0370,
            R.drawable.clippy_0371,
            R.drawable.clippy_0372,
            R.drawable.clippy_0373,
            R.drawable.clippy_0374,
            R.drawable.clippy_0375,
            R.drawable.clippy_0376,
            R.drawable.clippy_0377,
            R.drawable.clippy_0378,
            R.drawable.clippy_0379,
            R.drawable.clippy_0380,
            R.drawable.clippy_0381,
            R.drawable.clippy_0382,
            R.drawable.clippy_0383,
            R.drawable.clippy_0384,
            R.drawable.clippy_0385,
            R.drawable.clippy_0386,
            R.drawable.clippy_0387,
            R.drawable.clippy_0388,
            R.drawable.clippy_0389,
            R.drawable.clippy_0390,
            R.drawable.clippy_0391,
            R.drawable.clippy_0392,
            R.drawable.clippy_0393,
            R.drawable.clippy_0394,
            R.drawable.clippy_0395,
            R.drawable.clippy_0396,
            R.drawable.clippy_0397,
            R.drawable.clippy_0398,
            R.drawable.clippy_0399,
            R.drawable.clippy_0400,
            R.drawable.clippy_0401,
            R.drawable.clippy_0402,
            R.drawable.clippy_0403,
            R.drawable.clippy_0404,
            R.drawable.clippy_0405,
            R.drawable.clippy_0406,
            R.drawable.clippy_0407,
            R.drawable.clippy_0408,
            R.drawable.clippy_0409,
            R.drawable.clippy_0410,
            R.drawable.clippy_0411,
            R.drawable.clippy_0412,
            R.drawable.clippy_0413,
            R.drawable.clippy_0414,
            R.drawable.clippy_0415,
            R.drawable.clippy_0416,
            R.drawable.clippy_0417,
            R.drawable.clippy_0418,
            R.drawable.clippy_0419,
            R.drawable.clippy_0420,
            R.drawable.clippy_0421,
            R.drawable.clippy_0422,
            R.drawable.clippy_0423,
            R.drawable.clippy_0424,
            R.drawable.clippy_0425,
            R.drawable.clippy_0426,
            R.drawable.clippy_0427,
            R.drawable.clippy_0428,
            R.drawable.clippy_0429,
            R.drawable.clippy_0430,
            R.drawable.clippy_0431,
            R.drawable.clippy_0432,
            R.drawable.clippy_0433,
            R.drawable.clippy_0434,
            R.drawable.clippy_0435,
            R.drawable.clippy_0436,
            R.drawable.clippy_0437,
            R.drawable.clippy_0438,
            R.drawable.clippy_0439,
            R.drawable.clippy_0440,
            R.drawable.clippy_0441,
            R.drawable.clippy_0442,
            R.drawable.clippy_0443,
            R.drawable.clippy_0444,
            R.drawable.clippy_0445,
            R.drawable.clippy_0446,
            R.drawable.clippy_0447,
            R.drawable.clippy_0448,
            R.drawable.clippy_0449,
            R.drawable.clippy_0450,
            R.drawable.clippy_0451,
            R.drawable.clippy_0452,
            R.drawable.clippy_0453,
            R.drawable.clippy_0454,
            R.drawable.clippy_0455,
            R.drawable.clippy_0456,
            R.drawable.clippy_0457,
            R.drawable.clippy_0458,
            R.drawable.clippy_0459,
            R.drawable.clippy_0460,
            R.drawable.clippy_0461,
            R.drawable.clippy_0462,
            R.drawable.clippy_0463,
            R.drawable.clippy_0464,
            R.drawable.clippy_0465,
            R.drawable.clippy_0466,
            R.drawable.clippy_0467,
            R.drawable.clippy_0468,
            R.drawable.clippy_0469,
            R.drawable.clippy_0470,
            R.drawable.clippy_0471,
            R.drawable.clippy_0472,
            R.drawable.clippy_0473,
            R.drawable.clippy_0474,
            R.drawable.clippy_0475,
            R.drawable.clippy_0476,
            R.drawable.clippy_0477,
            R.drawable.clippy_0478,
            R.drawable.clippy_0479,
            R.drawable.clippy_0480,
            R.drawable.clippy_0481,
            R.drawable.clippy_0482,
            R.drawable.clippy_0483,
            R.drawable.clippy_0484,
            R.drawable.clippy_0485,
            R.drawable.clippy_0486,
            R.drawable.clippy_0487,
            R.drawable.clippy_0488,
            R.drawable.clippy_0489,
            R.drawable.clippy_0490,
            R.drawable.clippy_0491,
            R.drawable.clippy_0492,
            R.drawable.clippy_0493,
            R.drawable.clippy_0494,
            R.drawable.clippy_0495,
            R.drawable.clippy_0496,
            R.drawable.clippy_0497,
            R.drawable.clippy_0498,
            R.drawable.clippy_0499,
            R.drawable.clippy_0500,
            R.drawable.clippy_0501,
            R.drawable.clippy_0502,
            R.drawable.clippy_0503,
            R.drawable.clippy_0504,
            R.drawable.clippy_0505,
            R.drawable.clippy_0506,
            R.drawable.clippy_0507,
            R.drawable.clippy_0508,
            R.drawable.clippy_0509,
            R.drawable.clippy_0510,
            R.drawable.clippy_0511,
            R.drawable.clippy_0512,
            R.drawable.clippy_0513,
            R.drawable.clippy_0514,
            R.drawable.clippy_0515,
            R.drawable.clippy_0516,
            R.drawable.clippy_0517,
            R.drawable.clippy_0518,
            R.drawable.clippy_0519,
            R.drawable.clippy_0520,
            R.drawable.clippy_0521,
            R.drawable.clippy_0522,
            R.drawable.clippy_0523,
            R.drawable.clippy_0524,
            R.drawable.clippy_0525,
            R.drawable.clippy_0526,
            R.drawable.clippy_0527,
            R.drawable.clippy_0528,
            R.drawable.clippy_0529,
            R.drawable.clippy_0530,
            R.drawable.clippy_0531,
            R.drawable.clippy_0532,
            R.drawable.clippy_0533,
            R.drawable.clippy_0534,
            R.drawable.clippy_0535,
            R.drawable.clippy_0536,
            R.drawable.clippy_0537,
            R.drawable.clippy_0538,
            R.drawable.clippy_0539,
            R.drawable.clippy_0540,
            R.drawable.clippy_0541,
            R.drawable.clippy_0542,
            R.drawable.clippy_0543,
            R.drawable.clippy_0544,
            R.drawable.clippy_0545,
            R.drawable.clippy_0546,
            R.drawable.clippy_0547,
            R.drawable.clippy_0548,
            R.drawable.clippy_0549,
            R.drawable.clippy_0550,
            R.drawable.clippy_0551,
            R.drawable.clippy_0552,
            R.drawable.clippy_0553,
            R.drawable.clippy_0554,
            R.drawable.clippy_0555,
            R.drawable.clippy_0556,
            R.drawable.clippy_0557,
            R.drawable.clippy_0558,
            R.drawable.clippy_0559,
            R.drawable.clippy_0560,
            R.drawable.clippy_0561,
            R.drawable.clippy_0562,
            R.drawable.clippy_0563,
            R.drawable.clippy_0564,
            R.drawable.clippy_0565,
            R.drawable.clippy_0566,
            R.drawable.clippy_0567,
            R.drawable.clippy_0568,
            R.drawable.clippy_0569,
            R.drawable.clippy_0570,
            R.drawable.clippy_0571,
            R.drawable.clippy_0572,
            R.drawable.clippy_0573,
            R.drawable.clippy_0574,
            R.drawable.clippy_0575,
            R.drawable.clippy_0576,
            R.drawable.clippy_0577,
            R.drawable.clippy_0578,
            R.drawable.clippy_0579,
            R.drawable.clippy_0580,
            R.drawable.clippy_0581,
            R.drawable.clippy_0582,
            R.drawable.clippy_0583,
            R.drawable.clippy_0584,
            R.drawable.clippy_0585,
            R.drawable.clippy_0586,
            R.drawable.clippy_0587,
            R.drawable.clippy_0588,
            R.drawable.clippy_0589,
            R.drawable.clippy_0590,
            R.drawable.clippy_0591,
            R.drawable.clippy_0592,
            R.drawable.clippy_0593,
            R.drawable.clippy_0594,
            R.drawable.clippy_0595,
            R.drawable.clippy_0596,
            R.drawable.clippy_0597,
            R.drawable.clippy_0598,
            R.drawable.clippy_0599,
            R.drawable.clippy_0600,
            R.drawable.clippy_0601,
            R.drawable.clippy_0602,
            R.drawable.clippy_0603,
            R.drawable.clippy_0604,
            R.drawable.clippy_0605,
            R.drawable.clippy_0606,
            R.drawable.clippy_0607,
            R.drawable.clippy_0608,
            R.drawable.clippy_0609,
            R.drawable.clippy_0610,
            R.drawable.clippy_0611,
            R.drawable.clippy_0612,
            R.drawable.clippy_0613,
            R.drawable.clippy_0614,
            R.drawable.clippy_0615,
            R.drawable.clippy_0616,
            R.drawable.clippy_0617,
            R.drawable.clippy_0618,
            R.drawable.clippy_0619,
            R.drawable.clippy_0620,
            R.drawable.clippy_0621,
            R.drawable.clippy_0622,
            R.drawable.clippy_0623,
            R.drawable.clippy_0624,
            R.drawable.clippy_0625,
            R.drawable.clippy_0626,
            R.drawable.clippy_0627,
            R.drawable.clippy_0628,
            R.drawable.clippy_0629,
            R.drawable.clippy_0630,
            R.drawable.clippy_0631,
            R.drawable.clippy_0632,
            R.drawable.clippy_0633,
            R.drawable.clippy_0634,
            R.drawable.clippy_0635,
            R.drawable.clippy_0636,
            R.drawable.clippy_0637,
            R.drawable.clippy_0638,
            R.drawable.clippy_0639,
            R.drawable.clippy_0640,
            R.drawable.clippy_0641,
            R.drawable.clippy_0642,
            R.drawable.clippy_0643,
            R.drawable.clippy_0644,
            R.drawable.clippy_0645,
            R.drawable.clippy_0646,
            R.drawable.clippy_0647,
            R.drawable.clippy_0648,
            R.drawable.clippy_0649,
            R.drawable.clippy_0650,
            R.drawable.clippy_0651,
            R.drawable.clippy_0652,
            R.drawable.clippy_0653,
            R.drawable.clippy_0654,
            R.drawable.clippy_0655,
            R.drawable.clippy_0656,
            R.drawable.clippy_0657,
            R.drawable.clippy_0658,
            R.drawable.clippy_0659,
            R.drawable.clippy_0660,
            R.drawable.clippy_0661,
            R.drawable.clippy_0662,
            R.drawable.clippy_0663,
            R.drawable.clippy_0664,
            R.drawable.clippy_0665,
            R.drawable.clippy_0666,
            R.drawable.clippy_0667,
            R.drawable.clippy_0668,
            R.drawable.clippy_0669,
            R.drawable.clippy_0670,
            R.drawable.clippy_0671,
            R.drawable.clippy_0672,
            R.drawable.clippy_0673,
            R.drawable.clippy_0674,
            R.drawable.clippy_0675,
            R.drawable.clippy_0676,
            R.drawable.clippy_0677,
            R.drawable.clippy_0678,
            R.drawable.clippy_0679,
            R.drawable.clippy_0680,
            R.drawable.clippy_0681,
            R.drawable.clippy_0682,
            R.drawable.clippy_0683,
            R.drawable.clippy_0684,
            R.drawable.clippy_0685,
            R.drawable.clippy_0686,
            R.drawable.clippy_0687,
            R.drawable.clippy_0688,
            R.drawable.clippy_0689,
            R.drawable.clippy_0690,
            R.drawable.clippy_0691,
            R.drawable.clippy_0692,
            R.drawable.clippy_0693,
            R.drawable.clippy_0694,
            R.drawable.clippy_0695,
            R.drawable.clippy_0696,
            R.drawable.clippy_0697,
            R.drawable.clippy_0698,
            R.drawable.clippy_0699,
            R.drawable.clippy_0700,
            R.drawable.clippy_0701,
            R.drawable.clippy_0702,
            R.drawable.clippy_0703,
            R.drawable.clippy_0704,
            R.drawable.clippy_0705,
            R.drawable.clippy_0706,
            R.drawable.clippy_0707,
            R.drawable.clippy_0708,
            R.drawable.clippy_0709,
            R.drawable.clippy_0710,
            R.drawable.clippy_0711,
            R.drawable.clippy_0712,
            R.drawable.clippy_0713,
            R.drawable.clippy_0714,
            R.drawable.clippy_0715,
            R.drawable.clippy_0716,
            R.drawable.clippy_0717,
            R.drawable.clippy_0718,
            R.drawable.clippy_0719,
            R.drawable.clippy_0720,
            R.drawable.clippy_0721,
            R.drawable.clippy_0722,
            R.drawable.clippy_0723,
            R.drawable.clippy_0724,
            R.drawable.clippy_0725,
            R.drawable.clippy_0726,
            R.drawable.clippy_0727,
            R.drawable.clippy_0728,
            R.drawable.clippy_0729,
            R.drawable.clippy_0730,
            R.drawable.clippy_0731,
            R.drawable.clippy_0732,
            R.drawable.clippy_0733,
            R.drawable.clippy_0734,
            R.drawable.clippy_0735,
            R.drawable.clippy_0736,
            R.drawable.clippy_0737,
            R.drawable.clippy_0738,
            R.drawable.clippy_0739,
            R.drawable.clippy_0740,
            R.drawable.clippy_0741,
            R.drawable.clippy_0742,
            R.drawable.clippy_0743,
            R.drawable.clippy_0744,
            R.drawable.clippy_0745,
            R.drawable.clippy_0746,
            R.drawable.clippy_0747,
            R.drawable.clippy_0748,
            R.drawable.clippy_0749,
            R.drawable.clippy_0750,
            R.drawable.clippy_0751,
            R.drawable.clippy_0752,
            R.drawable.clippy_0753,
            R.drawable.clippy_0754,
            R.drawable.clippy_0755,
            R.drawable.clippy_0756,
            R.drawable.clippy_0757,
            R.drawable.clippy_0758,
            R.drawable.clippy_0759,
            R.drawable.clippy_0760,
            R.drawable.clippy_0761,
            R.drawable.clippy_0762,
            R.drawable.clippy_0763,
            R.drawable.clippy_0764,
            R.drawable.clippy_0765,
            R.drawable.clippy_0766,
            R.drawable.clippy_0767,
            R.drawable.clippy_0768,
            R.drawable.clippy_0769,
            R.drawable.clippy_0770,
            R.drawable.clippy_0771,
            R.drawable.clippy_0772,
            R.drawable.clippy_0773,
            R.drawable.clippy_0774,
            R.drawable.clippy_0775,
            R.drawable.clippy_0776,
            R.drawable.clippy_0777,
            R.drawable.clippy_0778,
            R.drawable.clippy_0779,
            R.drawable.clippy_0780,
            R.drawable.clippy_0781,
            R.drawable.clippy_0782,
            R.drawable.clippy_0783,
            R.drawable.clippy_0784,
            R.drawable.clippy_0785,
            R.drawable.clippy_0786,
            R.drawable.clippy_0787,
            R.drawable.clippy_0788,
            R.drawable.clippy_0789,
            R.drawable.clippy_0790,
            R.drawable.clippy_0791,
            R.drawable.clippy_0792,
            R.drawable.clippy_0793,
            R.drawable.clippy_0794,
            R.drawable.clippy_0795,
            R.drawable.clippy_0796,
            R.drawable.clippy_0797,
            R.drawable.clippy_0798,
            R.drawable.clippy_0799,
            R.drawable.clippy_0800,
            R.drawable.clippy_0801,
            R.drawable.clippy_0802,
            R.drawable.clippy_0803,
            R.drawable.clippy_0804,
            R.drawable.clippy_0805,
            R.drawable.clippy_0806,
            R.drawable.clippy_0807,
            R.drawable.clippy_0808,
            R.drawable.clippy_0809,
            R.drawable.clippy_0810,
            R.drawable.clippy_0811,
            R.drawable.clippy_0812,
            R.drawable.clippy_0813,
            R.drawable.clippy_0814,
            R.drawable.clippy_0815,
            R.drawable.clippy_0816,
            R.drawable.clippy_0817,
            R.drawable.clippy_0818,
            R.drawable.clippy_0819,
            R.drawable.clippy_0820,
            R.drawable.clippy_0821,
            R.drawable.clippy_0822,
            R.drawable.clippy_0823,
            R.drawable.clippy_0824,
            R.drawable.clippy_0825,
            R.drawable.clippy_0826,
            R.drawable.clippy_0827,
            R.drawable.clippy_0828,
            R.drawable.clippy_0829,
            R.drawable.clippy_0830,
            R.drawable.clippy_0831,
            R.drawable.clippy_0832,
            R.drawable.clippy_0833,
            R.drawable.clippy_0834,
            R.drawable.clippy_0835,
            R.drawable.clippy_0836,
            R.drawable.clippy_0837,
            R.drawable.clippy_0838,
            R.drawable.clippy_0839,
            R.drawable.clippy_0840,
            R.drawable.clippy_0841,
            R.drawable.clippy_0842,
            R.drawable.clippy_0843,
            R.drawable.clippy_0844,
            R.drawable.clippy_0845,
            R.drawable.clippy_0846,
            R.drawable.clippy_0847,
            R.drawable.clippy_0848,
            R.drawable.clippy_0849,
            R.drawable.clippy_0850,
            R.drawable.clippy_0851,
            R.drawable.clippy_0852,
            R.drawable.clippy_0853,
            R.drawable.clippy_0854,
            R.drawable.clippy_0855,
            R.drawable.clippy_0856,
            R.drawable.clippy_0857,
            R.drawable.clippy_0858,
            R.drawable.clippy_0859,
            R.drawable.clippy_0860,
            R.drawable.clippy_0861,
            R.drawable.clippy_0862,
            R.drawable.clippy_0863,
            R.drawable.clippy_0864,
            R.drawable.clippy_0865,
            R.drawable.clippy_0866,
            R.drawable.clippy_0867,
            R.drawable.clippy_0868,
            R.drawable.clippy_0869,
            R.drawable.clippy_0870,
            R.drawable.clippy_0871,
            R.drawable.clippy_0872,
            R.drawable.clippy_0873,
            R.drawable.clippy_0874,
            R.drawable.clippy_0875,
            R.drawable.clippy_0876,
            R.drawable.clippy_0877,
            R.drawable.clippy_0878,
            R.drawable.clippy_0879,
            R.drawable.clippy_0880,
            R.drawable.clippy_0881,
            R.drawable.clippy_0882,
            R.drawable.clippy_0883,
            R.drawable.clippy_0884,
            R.drawable.clippy_0885,
            R.drawable.clippy_0886,
            R.drawable.clippy_0887,
            R.drawable.clippy_0888,
            R.drawable.clippy_0889,
            R.drawable.clippy_0890,
            R.drawable.clippy_0891,
            R.drawable.clippy_0892,
            R.drawable.clippy_0893,
            R.drawable.clippy_0894,
            R.drawable.clippy_0895,
            R.drawable.clippy_0896,
            R.drawable.clippy_0897,
            R.drawable.clippy_0898,
            R.drawable.clippy_0899,
            R.drawable.clippy_0900,
            R.drawable.clippy_0901,
            R.drawable.clippy_0902,
            R.drawable.clippy_0903,
            R.drawable.clippy_0904,
            R.drawable.clippy_0905,
            R.drawable.clippy_0906,
            R.drawable.clippy_0907,
            R.drawable.clippy_0908,
            R.drawable.clippy_0909,
            R.drawable.clippy_0910,
            R.drawable.clippy_0911,
            R.drawable.clippy_0912,
            R.drawable.clippy_0913,
            R.drawable.clippy_0914,
            R.drawable.clippy_0915,
            R.drawable.clippy_0916,
            R.drawable.clippy_0917
    };

    private final int[] soundMapping = {
            R.raw.clippy_snd_01,
            R.raw.clippy_snd_02,
            R.raw.clippy_snd_03,
            R.raw.clippy_snd_04,
            R.raw.clippy_snd_05,
            R.raw.clippy_snd_06,
            R.raw.clippy_snd_07,
            R.raw.clippy_snd_08,
            R.raw.clippy_snd_09,
            R.raw.clippy_snd_10,
            R.raw.clippy_snd_11,
            R.raw.clippy_snd_12,
            R.raw.clippy_snd_13,
            R.raw.clippy_snd_14,
            R.raw.clippy_snd_15
    };


    @Override
    public int[] getMapping() {
        return mapping;
    }

    @Override
    public int[] getSoundMapping() {
        return soundMapping;
    }

    @Override
    public int getNumberRows() {
        return numberRows;
    }

    @Override
    public int getNumberColumns() {
        return numberColumns;
    }

    @Override
    public int getEmptyFrameId() {
        return R.drawable.clippy_0917;
    }

    @Override
    public int getFirstFrameId() {
        return R.drawable.clippy_0000;
    }
}
