package org.dsa.mediator.smithwaterman

import org.dsa.mediator.smithwaterman.adam.SmithWatermanConstantGapScoring
import org.dsa.utils.DSAFunSuite
import ssw.{ScoreMatrix, SSW}

/**
  * Created by xubo on 2016/12/31.
  */
class SmithWatermanConstantGapScoringSuite extends DSAFunSuite {

  test("SW 1") {
    val start = System.currentTimeMillis()
    val sw = new SmithWatermanConstantGapScoring("ACGT", "ACATGA", 1.0, 0.0, -1.0, -1.0)
    printSW(sw)

  }

  test("SW 2") {
    val start = System.currentTimeMillis()
    val sw = new SmithWatermanConstantGapScoring("GAC", "CCCAA", 1.0, 0.0, -1.0, -1.0)
    printSW(sw)

  }


  test("SW 3") {
    val start = System.currentTimeMillis()
    val sw = new SmithWatermanConstantGapScoring("PTHIKWGD", "PAGPFRIPPRXRXEFQ", 1.0, 0.0, -1.0, -1.0)
    printSW(sw)

  }

  test("SW 4") {
    val start = System.currentTimeMillis()
    val sw = new SmithWatermanConstantGapScoring("AAGCTA", "AAGACTAAAA", 1.0, 0.0, -1.0, -1.0)
    printSW(sw)

  }

  test("SW 5") {
    val start = System.currentTimeMillis()
    val sw = new SmithWatermanAffine("AAGCTA", "AAGACTAAAA", "blosum50", 12, 2)
    printSW(sw)

  }

  test("SW 6") {
    val start = System.currentTimeMillis()
    val query = "MAELTQGRVDIISRENGTLISQSVSGSSQSINLTEPSVVRISGTRDMVTQYERQGNDLVLHMRDGKTVRYQKFFLDDADGQHSELVFDDGVNPPEHALFPQTAEGAELASTSVTPTYESLDSVEPLLLADNANTSMGVITAGGLGVLGLAGLAVGVGGGGGGGGGNGGGNDGGTDPGTPVTPGTPAITLNAFAGDDVLDNAEKSSDQVLSGTTSNVEAGQIVTVTLGGQTYNAIVGADGSWSVTIPAAALAGLAAGTTTIDVSVTNAAGTTTTGSLPITVAAADPGTPAAPSISLNPFAGDNLLDNSEKTTPQTLSGSTSNVEAGQQVSVTLGGQSYNATVGADGSWSVSIPAAALSALAAGSSTISVSVTSAAGTAAAASLGIQVQAPDTTPGVPALTIAPFTGDDVLSNAEKTSDQILSGSTTNVEAGQIVTVTLGGQSYSGSVAGDGSWSITVPADALTALASGTTTVTATVANQAGTAASGSLTVNVEPPLTQPGTPVITIDQFAGDDILSNAEKSSDQVLSGTTTNVEAGQIVTITLGDQTYSAVVDAQGNWSVTLSPAALNALATGSVAISAVVTSQAGIEASENRAISIDTPVVPGPPSVTLDDFAGDNVLSNGEKATDQTLTGSTSNVEPGQLVTVTLGGQTYSGSVDAQGNWSVTLPSAALSALAAGETTLTVSVSNVAGDTVTGTLPITVEAPVTLPGEPTVTIGQFAGDDILSNDEKGTAQTLSGSTTNIEAGQTVTVTFGGQTYLGTVDGSGNWSVIVPAAALGNLPAGANTISVTVSNADGVSTTEIRDIAVETPVTEPGVPSLTLNDFAGDNLLSNDEKAVDQLVSGSTSNIETGQIVTVTLGGQTYSASVQGDGSWSVAVPSAALNALAAGTTTLTVSVSNAAGDTVTSTQPVTVEAPVTQPGEATVTIDAFAGDDILSNSEKASDQLLSGTTTNIGEGQQVTVTLNGETFTATVGADGSWSVSIPASSLAALTAGSAALNVTVTDQSGTQITETRDFLVESSGTGPGTPTLTLNAFADDNILSNDEKLEAQAVSGSTTNVEAGQIVTVTLGGQNYSGTVGADGSWNIAIPADALGALAAGSASLVVTVSNAAGTTVSDTLPITVEPPVTGPGQPTITLDQFAVDDVLSNDEKTSDQLLSGSSTNVEAGQVVTVTLGDQTYSATVGADGSWSLSVPASALAALTAGNATIAATVTNQAGVTVSEDRVIAVEAPAEPGTPTVTIALFAGDDLLDNSEKALDQTLSGSTSNVEAGQTVTITLGGQNYTALVNADGNWSTQIPSAALSALAAGSSTIAVSVTTAAGASATDTRDINVAPAAGEPGVVSISEPVSLDGFLNQQELSSDLIISGSAVGVNPGQAVQLNFNGSNYSAVVNSNGFWSVTVPAGDLTTITDGVKLISVTTTDATGALLTDSAQLNVVAATLPQITLEESAITDGLLNRVEAGSDLLIGGSTGKAVPGQQVELELNGKTYSTTVNADGSWSLALPAADLALLPQGANSLIITAIDEAGNRVDETLNFSVDTLPPNLIVAEVAGGDGILNSSEVAAGLPVSGSGALPGEVVSVSLNNESYSATVDGQGNWTVDVPPAALQALSDGSSYTLVVTLTDASGNTTTQTQPLAVSTQPPVATVTAPGGDGVLTNNELTTPLQISGTGTPGDSVTVELNGKSYTAIVDENSNWSASVPVADLGTLTNQTYPVTVTVTDPAGNISTQNTELRVATAVPALTLNDLSDDGVINVSDAQQPLIVSGTGDEGDIIRVTLNNVAYSAVVAQGGSWSVTVPASDLANVPNGTQPVSVVATDADGNTTPASSSLIFATTPPLLEVSTPGSDGYINIAEHTQDLTLDGRGGAQGDTVTVSFNGETYTATVDTDGAWSVTVPAAIISQLGDATYPVDVVLSDAYGNSSTVPTSVTVLAATSPALTINPVTGDNQVDSAEQLTDILVTGNVTGVPAGQPVLLTINGQSYDGVVQANGSWSVSLPAGSLGGAGDKTFTVAITDVAGNPALPAEGQFSVVTSPVPSLSIAPISDDNALNALEAQSDLILSGGSANLPANSPISVTLVTGGTVYTTTTDETGAWTLTVPAADLATLPQGTTTVTVSSGDVQNSLPLLVATAPLADPQINTPFTDGILNTDEASAAQTLTGTVLPGQAVSVSLGGTSYPATVDANGEWSVTIPADRLQALAQGSNPITVTVSDEAGNSTSISEPLIVDTGVPALTVNPIATDGTINEAETATDLTLSGTVTPGSSVSLALNGQTYDAVVAADGSWTATVPAANLQQLPDGRYDLNVTVTTVSGNVATTPVSVTVDTTAPDFSITAPAGDGVLNIAEQAAGFSFSGAGSEGDRVSVTLNGVTYTGTVDGDGNWALAVPPAALAGLTNGSSYPVVVTVTDAAGNSSSQNSALTVATTPPPLVVAPVSGDNALNVGELAEPLVVSGSGQNGDVVTVQLNDQLYSTTVGVNGQWTLQIAPEALAALPPGSNLVTVTETSANGNQSSQVVDLNVATASDVQPVLTIDSSTFAGDGIVTTAEQLQPITVRGSSTNVEPGQQVIISLNGIEYSGVVEASGNWSIILPAGALAELGEGNQTLNVSVINAVGNTASSPLDFSVDNTLPSIALAPVSDDNYLNAQELGGAVVVSGSTSGLPDGSIVTVLANGTTVSTTVTADGSWSLTLPAGTFTGTADGPQTITATASDANGQPLATSDATLTVIASALPVATPGVAFDNGILNGEEAAAGGAITGNTGVTGDGQTVVVTLGGNNYSGTVDAAGNWQVAVPADVLSALPQGNSGYTVVVSDIAGNSSQADGNLVVDTLPPVLNFITPSDGIINAAESQQPLTLTGSSEANALIVASFNGTTLSTTADVNGVWSLDLPATVYTGLGNGSYPLTVTASDAAGNATTTSRDLALKVEPGTLPTLTLDAFAGNNVVDGAERLTDQRLSGTTTNVEAGQLVTVTVDGTVYSAAVQASGAWSLIVPAGALAAINDGSASFTVAVSDVAGNTTTSNLTFDVNSNASGLAMDAISEDNYLNAQELGQALVVTGTSVNVAEGSTVTLLFNNISYTAQVSASGRWTVIVPAEALTALADGPYTLTVTATDSGGATLSSEAALSVLATLPAPQIVSAFGDGLLNSSEIATAQTLSGTTGITGDGQTVSVLLNGATYNGTVDGNGNWQISVPASALAGLPEDLLNYTVTVQDAAGNTGSSQGSVTVDLTPPTLTLNTVAGDNSINIAESTAPIVLSGNSNAEAGQQVTITLNNQTWTTTVDQNGEWNLTLPAGSLAGIPAGAYILTVTVSDAAGNPVTETREITVATASLAVSIDTPFGDGYLNLAEQGVGQTLTGSTGVAGNGQSVSVTLGGNDYSATVDAQGNWTLTLTADQVQTLGEGVTTLVVNASDAAGNSGSLTSAVTVDLTPPVLTVNAIGGDNIINSVEVLEAVAISGSASVVDAGQTVTVSFQNVDYTTQVLSDGTWEVTLPASVVQGLADGNYTVAVSMTDAAGNPATATQTLTRDADTLNLPTLTIGTISDDNFLNQAESLQDLNIGGTSNNLAEGQLVTVTLNNIQYSGTVAADGSWTVTVPAADLGNLPDGDQLLVVVSADTSGNPASASTSLVVLASEQVQPTLTMNVVAGDDVINATEATADVAVSGSSTLLAAGTLVTVSFNGTDYSTTLDADGNWTVNVPSAAFTSVDPGSTQIFTVTATDVAGNPATATQEVSFITTPPALTNITVDAGDTLNLAESLQDLTISGNTTGALPVTVTLNNVSYTTTADADGNWSLTIPTADLQQLADGPNAITVSVTDAAGNITTDSTTTLDVAFNTLPALTLNTPFGDALISAADAEGALTLSGDSTNLPEGSVVNISVGGQNFSTTTDVSGNWTLNLAPGALTGLADGLTQVVVSAADGAGNPAQVTAGVEILQSDPVSAAFDTTLFGDNIINISEAGSVQLVTGTSTVVPGQTLSVTVGDDNIPLSVTVDANGNWTASLPPEFIASLGTGEHTLTLTTTDRAGNSIETTKTFVAAITPIADPTLDTPFIDGRINAEEAASGGSLTGAVNINNAASVTVTVNGTVYSATLSADGSRWSLDLPPSVLQSLPDGNWPVSVTVTDANGNVGSTSGTILVAVNALPDVTLNLPFGDGALNAAEAAGEQILSGTTGITGAGQTVTVLISGFNNGQPLAATVQTDGSWSLALSPAQLATFTSGTHTITVTATDIAGNSDATAQNVVTEVALPVPTFNPGAFGGDNLLNISEAAAGVTLTGTTGSVGANQAVSITVDVNGSLYSGTVASNGNWTVNIPANALSSLGDGAQSLTVNVVDAAGNSASAQLPFTADLSAPQPAVNPEFLGSYVNGGDVAAGVTLSGTTGEAGANQTVQLTLGGTTYSATVDANGNWTVPLTTAELSLLPDATYPVTVTATDAAGNSTTINSSLVLDTTPPVLNVAAFTGDNALNYTESIQPQSLSGTAAGAEPGSVVSISLNDTLLGTAIVTANGNWSVTLTPEQMATFGPSTTLALAVTDLAGNTANGTVALTVDLTPPPGPLVTLGTVSGDNIISTQDVAGGVVVSGTSANLGAGGVVTVSINGQEYATTLDGSGNWSTAALPVSAFGNADGSVAITVNATDGTTPVTTSGNVLIDLTPPALTLNPFTGDNQVNGNESATSQAISGTADLSEVGRTVVVTFNNQTYNAVVQSNGTWSTTVPASAMQALPDGSSPVITAQLADAAGNVTTTTQTVTVDTAAPLVQVDAFLGDNVINAADLVLSQVLTGSAQGAEGQTIGLYLGDAAPIATATVGADGRWSIDLAPDVLSSLTDGALVFGVRVNDGAGNQTDATLTVNKVVNSALTLVVDSVFGDGTLSALDTTVAQTISGVATSAGVGATVSVVLGGTTLSASVGQDGKWAIVVPPSVLGLLSDGNLALDVTLTDAAGNTRTVGETVTAIVDAVPVVGALTGLFGGDNLLNIAEAAAGQLVSGVIENAAVGSQVTVTFGSKSYSTTVQAGGVWSVNLPGSDLTSLLDGNLTLGVSVRDVAGNVASSSASIGIFTQTPSISLTSLFGDGVLNLADIATGQVISGVVNNVAQGSVVTLSVGNSQVTATVGAGGAFSATVTPDILGTLAQGNLTVGASVTDAAGNTASTSAGIRVDTLLPTITVNPLFGDGLLNVADSLVTQVIGGVIGGAEAGSRVVVSVGSQQLVTATDANGNFNVSLTPALLQGIADGNLTVGISVTDSAGNTNSTTAGALVGIHNLPKVTLSPLFGDGVLNLAESLVTQTISGTVSGLAAGSSVRLTIGNTIVDAQVNADGSFSTAVSPAILSTLLNGNFTVSAAVTDPVGNVSSTSAGVSLGLFQPTLSVNTVFGDGVLSAADLASNQTLSGTSSLSAGSTVSATLNGLTYTTKVVSGGNWSITVPKADLVAITDGSKTVTVTGTDAYGNVANSSGTLSVISQSTPVVAITSLFGDSALSAADVKTAQTISGTASNAEGSVVRVTLGGQTYSTTVSSNGNWSLSVPAANLAAITDGLQTVTASVTNGAGSIGNTSASLGVVSHTTPTVSVNSFFGGDGYLNIAEANSTETIRGTSTNAAGGTVTVNVAGNILTTTIGANGAWSISVPSATLKGIADGSHPLTVTVADIGGNTATSTSSFTALSHNQPLVGVDPVLSIVGSLLAGLVVQGGSLNAAQGSKVNVTLLLSNGNNGPTLNTTTDALGRYAVNFSPSLLSVGGLLLSLGTLAKVTITDAAGNSYSTTNTLLLGAVLPVTLAANESVALFSVADDSATVASAETQHNSSTSSDESSAHVAVASTLITEADTVTPVGSSENAAANDAAVAAAPAEEAGYTIGGVVITLADGSTIEGATVTGSSGADIVTVSDLNFTHIDGGAGTDTLVLNGEHLSLDLTALGLKVEHIEVLDLGKTGTNSVKLDLNEALNITDKQSDDLLIKGADGSQVTLANSNGGIWEVTGERNVEGRVFEIYHNSALTSDNTLGDVLVQQNLQVHVV"
    val sw = new SmithWatermanAffine(query, query, "blosum50", 12, 2)
    println("sw.maxScore:" + sw.maxScore)
    println("sw.xStart:" + sw.xStart)
    println("sw.yStart:" + sw.yStart)
    println("sw.cigarX:" + sw.cigarX)
    println("sw.cigarY:" + sw.cigarY)

  }

  test("SW 7") {
    val start = System.currentTimeMillis()
    val query = "MAELTQGRVDIISRENGTLISQSVSGSSQSINLTEPSVVRISGTRDMVTQYERQGNDLVLHMRDGKTVRYQKFFLDDADGQHSELVFDDGVNPPEHALFPQTAEGAELASTSVTPTYESLDSVEPLLLADNANTSMGVITAGGLGVLGLAGLAVGVGGGGGGGGGNGGGNDGGTDPGTPVTPGTPAITLNAFAGDDVLDNAEKSSDQVLSGTTSNVEAGQIVTVTLGGQTYNAIVGADGSWSVTIPAAALAGLAAGTTTIDVSVTNAAGTTTTGSLPITVAAADPGTPAAPSISLNPFAGDNLLDNSEKTTPQTLSGSTSNVEAGQQVSVTLGGQSYNATVGADGSWSVSIPAAALSALAAGSSTISVSVTSAAGTAAAASLGIQVQAPDTTPGVPALTIAPFTGDDVLSNAEKTSDQILSGSTTNVEAGQIVTVTLGGQSYSGSVAGDGSWSITVPADALTALASGTTTVTATVANQAGTAASGSLTVNVEPPLTQPGTPVITIDQFAGDDILSNAEKSSDQVLSGTTTNVEAGQIVTITLGDQTYSAVVDAQGNWSVTLSPAALNALATGSVAISAVVTSQAGIEASENRAISIDTPVVPGPPSVTLDDFAGDNVLSNGEKATDQTLTGSTSNVEPGQLVTVTLGGQTYSGSVDAQGNWSVTLPSAALSALAAGETTLTVSVSNVAGDTVTGTLPITVEAPVTLPGEPTVTIGQFAGDDILSNDEKGTAQTLSGSTTNIEAGQTVTVTFGGQTYLGTVDGSGNWSVIVPAAALGNLPAGANTISVTVSNADGVSTTEIRDIAVETPVTEPGVPSLTLNDFAGDNLLSNDEKAVDQLVSGSTSNIETGQIVTVTLGGQTYSASVQGDGSWSVAVPSAALNALAAGTTTLTVSVSNAAGDTVTSTQPVTVEAPVTQPGEATVTIDAFAGDDILSNSEKASDQLLSGTTTNIGEGQQVTVTLNGETFTATVGADGSWSVSIPASSLAALTAGSAALNVTVTDQSGTQITETRDFLVESSGTGPGTPTLTLNAFADDNILSNDEKLEAQAVSGSTTNVEAGQIVTVTLGGQNYSGTVGADGSWNIAIPADALGALAAGSASLVVTVSNAAGTTVSDTLPITVEPPVTGPGQPTITLDQFAVDDVLSNDEKTSDQLLSGSSTNVEAGQVVTVTLGDQTYSATVGADGSWSLSVPASALAALTAGNATIAATVTNQAGVTVSEDRVIAVEAPAEPGTPTVTIALFAGDDLLDNSEKALDQTLSGSTSNVEAGQTVTITLGGQNYTALVNADGNWSTQIPSAALSALAAGSSTIAVSVTTAAGASATDTRDINVAPAAGEPGVVSISEPVSLDGFLNQQELSSDLIISGSAVGVNPGQAVQLNFNGSNYSAVVNSNGFWSVTVPAGDLTTITDGVKLISVTTTDATGALLTDSAQLNVVAATLPQITLEESAITDGLLNRVEAGSDLLIGGSTGKAVPGQQVELELNGKTYSTTVNADGSWSLALPAADLALLPQGANSLIITAIDEAGNRVDETLNFSVDTLPPNLIVAEVAGGDGILNSSEVAAGLPVSGSGALPGEVVSVSLNNESYSATVDGQGNWTVDVPPAALQALSDGSSYTLVVTLTDASGNTTTQTQPLAVSTQPPVATVTAPGGDGVLTNNELTTPLQISGTGTPGDSVTVELNGKSYTAIVDENSNWSASVPVADLGTLTNQTYPVTVTVTDPAGNISTQNTELRVATAVPALTLNDLSDDGVINVSDAQQPLIVSGTGDEGDIIRVTLNNVAYSAVVAQGGSWSVTVPASDLANVPNGTQPVSVVATDADGNTTPASSSLIFATTPPLLEVSTPGSDGYINIAEHTQDLTLDGRGGAQGDTVTVSFNGETYTATVDTDGAWSVTVPAAIISQLGDATYPVDVVLSDAYGNSSTVPTSVTVLAATSPALTINPVTGDNQVDSAEQLTDILVTGNVTGVPAGQPVLLTINGQSYDGVVQANGSWSVSLPAGSLGGAGDKTFTVAITDVAGNPALPAEGQFSVVTSPVPSLSIAPISDDNALNALEAQSDLILSGGSANLPANSPISVTLVTGGTVYTTTTDETGAWTLTVPAADLATLPQGTTTVTVSSGDVQNSLPLLVATAPLADPQINTPFTDGILNTDEASAAQTLTGTVLPGQAVSVSLGGTSYPATVDANGEWSVTIPADRLQALAQGSNPITVTVSDEAGNSTSISEPLIVDTGVPALTVNPIATDGTINEAETATDLTLSGTVTPGSSVSLALNGQTYDAVVAADGSWTATVPAANLQQLPDGRYDLNVTVTTVSGNVATTPVSVTVDTTAPDFSITAPAGDGVLNIAEQAAGFSFSGAGSEGDRVSVTLNGVTYTGTVDGDGNWALAVPPAALAGLTNGSSYPVVVTVTDAAGNSSSQNSALTVATTPPPLVVAPVSGDNALNVGELAEPLVVSGSGQNGDVVTVQLNDQLYSTTVGVNGQWTLQIAPEALAALPPGSNLVTVTETSANGNQSSQVVDLNVATASDVQPVLTIDSSTFAGDGIVTTAEQLQPITVRGSSTNVEPGQQVIISLNGIEYSGVVEASGNWSIILPAGALAELGEGNQTLNVSVINAVGNTASSPLDFSVDNTLPSIALAPVSDDNYLNAQELGGAVVVSGSTSGLPDGSIVTVLANGTTVSTTVTADGSWSLTLPAGTFTGTADGPQTITATASDANGQPLATSDATLTVIASALPVATPGVAFDNGILNGEEAAAGGAITGNTGVTGDGQTVVVTLGGNNYSGTVDAAGNWQVAVPADVLSALPQGNSGYTVVVSDIAGNSSQADGNLVVDTLPPVLNFITPSDGIINAAESQQPLTLTGSSEANALIVASFNGTTLSTTADVNGVWSLDLPATVYTGLGNGSYPLTVTASDAAGNATTTSRDLALKVEPGTLPTLTLDAFAGNNVVDGAERLTDQRLSGTTTNVEAGQLVTVTVDGTVYSAAVQASGAWSLIVPAGALAAINDGSASFTVAVSDVAGNTTTSNLTFDVNSNASGLAMDAISEDNYLNAQELGQALVVTGTSVNVAEGSTVTLLFNNISYTAQVSASGRWTVIVPAEALTALADGPYTLTVTATDSGGATLSSEAALSVLATLPAPQIVSAFGDGLLNSSEIATAQTLSGTTGITGDGQTVSVLLNGATYNGTVDGNGNWQISVPASALAGLPEDLLNYTVTVQDAAGNTGSSQGSVTVDLTPPTLTLNTVAGDNSINIAESTAPIVLSGNSNAEAGQQVTITLNNQTWTTTVDQNGEWNLTLPAGSLAGIPAGAYILTVTVSDAAGNPVTETREITVATASLAVSIDTPFGDGYLNLAEQGVGQTLTGSTGVAGNGQSVSVTLGGNDYSATVDAQGNWTLTLTADQVQTLGEGVTTLVVNASDAAGNSGSLTSAVTVDLTPPVLTVNAIGGDNIINSVEVLEAVAISGSASVVDAGQTVTVSFQNVDYTTQVLSDGTWEVTLPASVVQGLADGNYTVAVSMTDAAGNPATATQTLTRDADTLNLPTLTIGTISDDNFLNQAESLQDLNIGGTSNNLAEGQLVTVTLNNIQYSGTVAADGSWTVTVPAADLGNLPDGDQLLVVVSADTSGNPASASTSLVVLASEQVQPTLTMNVVAGDDVINATEATADVAVSGSSTLLAAGTLVTVSFNGTDYSTTLDADGNWTVNVPSAAFTSVDPGSTQIFTVTATDVAGNPATATQEVSFITTPPALTNITVDAGDTLNLAESLQDLTISGNTTGALPVTVTLNNVSYTTTADADGNWSLTIPTADLQQLADGPNAITVSVTDAAGNITTDSTTTLDVAFNTLPALTLNTPFGDALISAADAEGALTLSGDSTNLPEGSVVNISVGGQNFSTTTDVSGNWTLNLAPGALTGLADGLTQVVVSAADGAGNPAQVTAGVEILQSDPVSAAFDTTLFGDNIINISEAGSVQLVTGTSTVVPGQTLSVTVGDDNIPLSVTVDANGNWTASLPPEFIASLGTGEHTLTLTTTDRAGNSIETTKTFVAAITPIADPTLDTPFIDGRINAEEAASGGSLTGAVNINNAASVTVTVNGTVYSATLSADGSRWSLDLPPSVLQSLPDGNWPVSVTVTDANGNVGSTSGTILVAVNALPDVTLNLPFGDGALNAAEAAGEQILSGTTGITGAGQTVTVLISGFNNGQPLAATVQTDGSWSLALSPAQLATFTSGTHTITVTATDIAGNSDATAQNVVTEVALPVPTFNPGAFGGDNLLNISEAAAGVTLTGTTGSVGANQAVSITVDVNGSLYSGTVASNGNWTVNIPANALSSLGDGAQSLTVNVVDAAGNSASAQLPFTADLSAPQPAVNPEFLGSYVNGGDVAAGVTLSGTTGEAGANQTVQLTLGGTTYSATVDANGNWTVPLTTAELSLLPDATYPVTVTATDAAGNSTTINSSLVLDTTPPVLNVAAFTGDNALNYTESIQPQSLSGTAAGAEPGSVVSISLNDTLLGTAIVTANGNWSVTLTPEQMATFGPSTTLALAVTDLAGNTANGTVALTVDLTPPPGPLVTLGTVSGDNIISTQDVAGGVVVSGTSANLGAGGVVTVSINGQEYATTLDGSGNWSTAALPVSAFGNADGSVAITVNATDGTTPVTTSGNVLIDLTPPALTLNPFTGDNQVNGNESATSQAISGTADLSEVGRTVVVTFNNQTYNAVVQSNGTWSTTVPASAMQALPDGSSPVITAQLADAAGNVTTTTQTVTVDTAAPLVQVDAFLGDNVINAADLVLSQVLTGSAQGAEGQTIGLYLGDAAPIATATVGADGRWSIDLAPDVLSSLTDGALVFGVRVNDGAGNQTDATLTVNKVVNSALTLVVDSVFGDGTLSALDTTVAQTISGVATSAGVGATVSVVLGGTTLSASVGQDGKWAIVVPPSVLGLLSDGNLALDVTLTDAAGNTRTVGETVTAIVDAVPVVGALTGLFGGDNLLNIAEAAAGQLVSGVIENAAVGSQVTVTFGSKSYSTTVQAGGVWSVNLPGSDLTSLLDGNLTLGVSVRDVAGNVASSSASIGIFTQTPSISLTSLFGDGVLNLADIATGQVISGVVNNVAQGSVVTLSVGNSQVTATVGAGGAFSATVTPDILGTLAQGNLTVGASVTDAAGNTASTSAGIRVDTLLPTITVNPLFGDGLLNVADSLVTQVIGGVIGGAEAGSRVVVSVGSQQLVTATDANGNFNVSLTPALLQGIADGNLTVGISVTDSAGNTNSTTAGALVGIHNLPKVTLSPLFGDGVLNLAESLVTQTISGTVSGLAAGSSVRLTIGNTIVDAQVNADGSFSTAVSPAILSTLLNGNFTVSAAVTDPVGNVSSTSAGVSLGLFQPTLSVNTVFGDGVLSAADLASNQTLSGTSSLSAGSTVSATLNGLTYTTKVVSGGNWSITVPKADLVAITDGSKTVTVTGTDAYGNVANSSGTLSVISQSTPVVAITSLFGDSALSAADVKTAQTISGTASNAEGSVVRVTLGGQTYSTTVSSNGNWSLSVPAANLAAITDGLQTVTASVTNGAGSIGNTSASLGVVSHTTPTVSVNSFFGGDGYLNIAEANSTETIRGTSTNAAGGTVTVNVAGNILTTTIGANGAWSISVPSATLKGIADGSHPLTVTVADIGGNTATSTSSFTALSHNQPLVGVDPVLSIVGSLLAGLVVQGGSLNAAQGSKVNVTLLLSNGNNGPTLNTTTDALGRYAVNFSPSLLSVGGLLLSLGTLAKVTITDAAGNSYSTTNTLLLGAVLPVTLAANESVALFSVADDSATVASAETQHNSSTSSDESSAHVAVASTLITEADTVTPVGSSENAAANDAAVAAAPAEEAGYTIGGVVITLADGSTIEGATVTGSSGADIVTVSDLNFTHIDGGAGTDTLVLNGEHLSLDLTALGLKVEHIEVLDLGKTGTNSVKLDLNEALNITDKQSDDLLIKGADGSQVTLANSNGGIWEVTGERNVEGRVFEIYHNSALTSDNTLGDVLVQQNLQVHVV"
    val ref = "AMAELTQGRVDIISRENGTLISQSVSGSSQSGTRDMVTQYERQGNDLVLHMRDGKTVRYQKFFLDDADGQHSELVFDDGVNPPEHALFPQTAEGAELASTSVTPTYESLDSVEPLLLADNANTSMGVITAGGLGVLGLAGLAVGVGGGGGGGGGNGGGNDGGTDPGTPVTPGTPAITLNAFAGDDVLDNAEKSSDQVLSGTTSNVEAGQIVTVTLGGQTYNAIVGADGSWSVTIPAAALAGLAAGTTTIDVSVTNAAGTTTTGSLPITVAAADPGTPAAPSISLNPFAGDNLLDNSEKTTPQTLSGSTSNVEAGQQVSVTLGGQSYNATVGADGSWSVSIPAAALSALAAGSSTISVSVTSAAGTAAAASLGIQVQAPDTTPGVPALTIAPFTGDDVLSNAEKTSDQILSGSTTNVEAGQIVTVTLGGQSYSGSVAGDGSWSITVPADALTALASGTTTVTATVANQAGTAASGSLTVNVEPPLTQPGTPVITIDQFAGDDILSNAEKSSDQVLSGTTTNVEAGQIVTITLGDQTYSAVVDAQGNWSVTLSPAALNALATGSVAISAVVTSQAGIEASENRAISIDTPVVPGPPSVTLDDFAGDNVLSNGEKATDQTLTGSTSNVEPGQLVTVTLGGQTYSGSVDAQGNWSVTLPSAALSALAAGETTLTVSVSNVAGDTVTGTLPITVEAPVTLPGEPTVTIGQFAGDDILSNDEKGTAQTLSGSTTNIEAGQTVTVTFGGQTYLGTVDGSGNWSVIVPAAALGNLPAGANTISVTVSNADGVSTTEIRDIAVETPVTEPGVPSLTLNDFAGDNLLSNDEKAVDQLVSGSTSNIETGQIVTVTLGGQTYSASVQGDGSWSVAVPSAALNALAAGTTTLTVSVSNAAGDTVTSTQPVTVEAPVTQPGEATVTIDAFAGDDILSNSEKASDQLLSGTTTNIGEGQQVTVTLNGETFTATVGADGSWSVSIPASSLAALTAGSAALNVTVTDQSGTQITETRDFLVESSGTGPGTPTLTLNAFADDNILSNDEKLEAQAVSGSTTNVEAGQIVTVTLGGQNYSGTVGADGSWNIAIPADALGALAAGSASLVVTVSNAAGTTVSDTLPITVEPPVTGPGQPTITLDQFAVDDVLSNDEKTSDQLLSGSSTNVEAGQVVTVTLGDQTYSATVGADGSWSLSVPASALAALTAGNATIAATVTNQAGVTVSEDRVIAVEAPAEPGTPTVTIALFAGDDLLDNSEKALDQTLSGSTSNVEAGQTVTITLGGQNYTALVNADGNWSTQIPSAALSALAAGSSTIAVSVTTAAGASATDTRDINVAPAAGEPGVVSISEPVSLDGFLNQQELSSDLIISGSAVGVNPGQAVQLNFNGSNYSAVVNSNGFWSVTVPAGDLTTITDGVKLISVTTTDATGALLTDSAQLNVVAATLPQITLEESAITDGLLNRVEAGSDLLIGGSTGKAVPGQQVELELNGKTYSTTVNADGSWSLALPAADLALLPQGANSLIITAIDEAGNRVDETLNFSVDTLPPNLIVAEVAGGDGILNSSEVAAGLPVSGSGALPGEVVSVSLNNESYSATVDGQGNWTVDVPPAALQALSDGSSYTLVVTLTDASGNTTTQTQPLAVSTQPPVATVTAPGGDGVLTNNELTTPLQISGTGTPGDSVTVELNGKSYTAIVDENSNWSASVPVADLGTLTNQTYPVTVTVTDPAGNISTQNTELRVATAVPALTLNDLSDDGVINVSDAQQPLIVSGTGDEGDIIRVTLNNVAYSAVVAQGGSWSVTVPASDLANVPNGTQPVSVVATDADGNTTPASSSLIFATTPPLLEVSTPGSDGYINIAEHTQDLTLDGRGGAQGDTVTVSFNGETYTATVDTDGAWSVTVPAAIISQLGDATYPVDVVLSDAYGNSSTVPTSVTVLAATSPALTINPVTGDNQVDSAEQLTDILVTGNVTGVPAGQPVLLTINGQSYDGVVQANGSWSVSLPAGSLGGAGDKTFTVAITDVAGNPALPAEGQFSVVTSPVPSLSIAPISDDNALNALEAQSDLILSGGSANLPANSPISVTLVTGGTVYTTTTDETGAWTLTVPAADLATLPQGTTTVTVSSGDVQNSLPLLVATAPLADPQINTPFTDGILNTDEASAAQTLTGTVLPGQAVSVSLGGTSYPATVDANGEWSVTIPADRLQALAQGSNPITVTVSDEAGNSTSISEPLIVDTGVPALTVNPIATDGTINEAETATDLTLSGTVTPGSSVSLALNGQTYDAVVAADGSWTATVPAANLQQLPDGRYDLNVTVTTVSGNVATTPVSVTVDTTAPDFSITAPAGDGVLNIAEQAAGFSFSGAGSEGDRVSVTLNGVTYTGTVDGDGNWALAVPPAALAGLTNGSSYPVVVTVTDAAGNSSSQNSALTVATTPPPLVVAPVSGDNALNVGELAEPLVVSGSGQNGDVVTVQLNDQLYSTTVGVNGQWTLQIAPEALAALPPGSNLVTVTETSANGNQSSQVVDLNVATASDVQPVLTIDSSTFAGDGIVTTAEQLQPITVRGSSTNVEPGQQVIISLNGIEYSGVVEASGNWSIILPAGALAELGEGNQTLNVSVINAVGNTASSPLDFSVDNTLPSIALAPVSDDNYLNAQELGGAVVVSGSTSGLPDGSIVTVLANGTTVSTTVTADGSWSLTLPAGTFTGTADGPQTITATASDANGQPLATSDATLTVIASALPVATPGVAFDNGILNGEEAAAGGAITGNTGVTGDGQTVVVTLGGNNYSGTVDAAGNWQVAVPADVLSALPQGNSGYTVVVSDIAGNSSQADGNLVVDTLPPVLNFITPSDGIINAAESQQPLTLTGSSEANALIVASFNGTTLSTTADVNGVWSLDLPATVYTGLGNGSYPLTVTASDAAGNATTTSRDLALKVEPGTLPTLTLDAFAGNNVVDGAERLTDQRLSGTTTNVEAGQLVTVTVDGTVYSAAVQASGAWSLIVPAGALAAINDGSASFTVAVSDVAGNTTTSNLTFDVNSNASGLAMDAISEDNYLNAQELGQALVVTGTSVNVAEGSTVTLLFNNISYTAQVSASGRWTVIVPAEALTALADGPYTLTVTATDSGGATLSSEAALSVLATLPAPQIVSAFGDGLLNSSEIATAQTLSGTTGITGDGQTVSVLLNGATYNGTVDGNGNWQISVPASALAGLPEDLLNYTVTVQDAAGNTGSSQGSVTVDLTPPTLTLNTVAGDNSINIAESTAPIVLSGNSNAEAGQQVTITLNNQTWTTTVDQNGEWNLTLPAGSLAGIPAGAYILTVTVSDAAGNPVTETREITVATASLAVSIDTPFGDGYLNLAEQGVGQTLTGSTGVAGNGQSVSVTLGGNDYSATVDAQGNWTLTLTADQVQTLGEGVTTLVVNASDAAGNSGSLTSAVTVDLTPPVLTVNAIGGDNIINSVEVLEAVAISGSASVVDAGQTVTVSFQNVDYTTQVLSDGTWEVTLPASVVQGLADGNYTVAVSMTDAAGNPATATQTLTRDADTLNLPTLTIGTISDDNFLNQAESLQDLNIGGTSNNLAEGQLVTVTLNNIQYSGTVAADGSWTVTVPAADLGNLPDGDQLLVVVSADTSGNPASASTSLVVLASEQVQPTLTMNVVAGDDVINATEATADVAVSGSSTLLAAGTLVTVSFNGTDYSTTLDADGNWTVNVPSAAFTSVDPGSTQIFTVTATDVAGNPATATQEVSFITTPPALTNITVDAGDTLNLAESLQDLTISGNTTGALPVTVTLNNVSYTTTADADGNWSLTIPTADLQQLADGPNAITVSVTDAAGNITTDSTTTLDVAFNTLPALTLNTPFGDALISAADAEGALTLSGDSTNLPEGSVVNISVGGQNFSTTTDVSGNWTLNLAPGALTGLADGLTQVVVSAADGAGNPAQVTAGVEILQSDPVSAAFDTTLFGDNIINISEAGSVQLVTGTSTVVPGQTLSVTVGDDNIPLSVTVDANGNWTASLPPEFIASLGTGEHTLTLTTTDRAGNSIETTKTFVAAITPIADPTLDTPFIDGRINAEEAASGGSLTGAVNINNAASVTVTVNGTVYSATLSADGSRWSLDLPPSVLQSLPDGNWPVSVTVTDANGNVGSTSGTILVAVNALPDVTLNLPFGDGALNAAEAAGEQILSGTTGITGAGQTVTVLISGFNNGQPLAATVQTDGSWSLALSPAQLATFTSGTHTITVTATDIAGNSDATAQNVVTEVALPVPTFNPGAFGGDNLLNISEAAAGVTLTGTTGSVGANQAVSITVDVNGSLYSGTVASNGNWTVNIPANALSSLGDGAQSLTVNVVDAAGNSASAQLPFTADLSAPQPAVNPEFLGSYVNGGDVAAGVTLSGTTGEAGANQTVQLTLGGTTYSATVDANGNWTVPLTTAELSLLPDATYPVTVTATDAAGNSTTINSSLVLDTTPPVLNVAAFTGDNALNYTESIQPQSLSGTAAGAEPGSVVSISLNDTLLGTAIVTANGNWSVTLTPEQMATFGPSTTLALAVTDLAGNTANGTVALTVDLTPPPGPLVTLGTVSGDNIISTQDVAGGVVVSGTSANLGAGGVVTVSINGQEYATTLDGSGNWSTAALPVSAFGNADGSVAITVNATDGTTPVTTSGNVLIDLTPPALTLNPFTGDNQVNGNESATSQAISGTADLSEVGRTVVVTFNNQTYNAVVQSNGTWSTTVPASAMQALPDGSSPVITAQLADAAGNVTTTTQTVTVDTAAPLVQVDAFLGDNVINAADLVLSQVLTGSAQGAEGQTIGLYLGDAAPIATATVGADGRWSIDLAPDVLSSLTDGALVFGVRVNDGAGNQTDATLTVNKVVNSALTLVVDSVFGDGTLSALDTTVAQTISGVATSAGVGATVSVVLGGTTLSASVGQDGKWAIVVPPSVLGLLSDGNLALDVTLTDAAGNTRTVGETVTAIVDAVPVVGALTGLFGGDNLLNIAEAAAGQLVSGVIENAAVGSQVTVTFGSKSYSTTVQAGGVWSVNLPGSDLTSLLDGNLTLGVSVRDVAGNVASSSASIGIFTQTPSISLTSLFGDGVLNLADIATGQVISGVVNNVAQGSVVTLSVGNSQVTATVGAGGAFSATVTPDILGTLAQGNLTVGASVTDAAGNTASTSAGIRVDTLLPTITVNPLFGDGLLNVADSLVTQVIGGVIGGAEAGSRVVVSVGSQQLVTATDANGNFNVSLTPALLQGIADGNLTVGISVTDSAGNTNSTTAGALVGIHNLPKVTLSPLFGDGVLNLAESLVTQTISGTVSGLAAGSSVRLTIGNTIVDAQVNADGSFSTAVSPAILSTLLNGNFTVSAAVTDPVGNVSSTSAGVSLGLFQPTLSVNTVFGDGVLSAADLASNQTLSGTSSLSAGSTVSATLNGLTYTTKVVSGGNWSITVPKADLVAITDGSKTVTVTGTDAYGNVANSSGTLSVISQSTPVVAITSLFGDSALSAADVKTAQTISGTASNAEGSVVRVTLGGQTYSTTVSSNGNWSLSVPAANLAAITDGLQTVTASVTNGAGSIGNTSASLGVVSHTTPTVSVNSFFGGDGYLNIAEANSTETIRGTSTNAAGGTVTVNVAGNILTTTIGANGAWSISVPSATLKGIADGSHPLTVTVADIGGNTATSTSSFTALSHNQPLVGVDPVLSIVGSLLAGLVVQGGSLNAAQGSKVNVTLLLSNGNNGPTLNTTTDALGRYAVNFSPSLLSVGGLLLSLGTLAKVTITDAAGNSYSTTNTLLLGAVLPVTLAANESVALFSVADDSATVASAETQHNSSTSSDESSAHVAVASTLITEADTVTPVGSSENAAANDAAVAAAPAEEAGYTIGGVVITLADGSTIEGATVTGSSGADIVTVSDLNFTHIDGGAGTDTLVLNGEHLSLDLTALGLKVEHIEVLDLGKTGTNSVKLDLNEALNITDKQSDDLLIKGADGSQVTLANSNGGIWEVTGERNVEGRVFEIYHNSALTSDNTLGDVLVQQNLQVHVV"

    val sw = new SmithWatermanAffine(query, ref, "blosum50", 12, 2)
    println("sw.maxScore:" + sw.maxScore)
    println("sw.xStart:" + sw.xStart)
    println("sw.yStart:" + sw.yStart)
    println("sw.cigarX:" + sw.cigarX)
    println("sw.cigarY:" + sw.cigarY)

  }

  test("SW 8") {
    val start = System.currentTimeMillis()
    val query = "MVASSYTKLESDPPTKPSLPWRFSSSLIMGVTGAITRFFYYGLSNVEVIGLERFKATLDRRENPEERERGLITVSNHVSVMDDPLIWGVLPLKYGFNPSNHRWSLGSYDICFQNKVLSTFFTLGQVLPTHRGAYSENGGLFQPTIAQAIRMLSAQPFTTRYEPPIQKAKKKISMRPKDPDIVDPFSSGDLTYTTNGIDVFPAPSAYTSRKHSWIHIFPEGRVHQHPKKSLRYFKWGVSRLILESEPLPEIVPIFIDGNQDIMHESREFPRFLPRVGKNIRIAFGESIDGERIFGDLRLRWQKLVQLQEEALRRKGLDDNIEMGELTEGLKYYKEAVALREEVTTRVRQEVLKVRRSLGYEDEDPKQGLVETWISEGRSDGKKKKDGSWVGDT"
    val ref = "MGLTGTLSRGFLYGLNYMEVIGLDRFLETLDKRKDVGGRERGLLTVSNHVSVLDDPLIWGSLPLSYAFSPKDLRWSLGSYDICFKNKFLSAFFSVGQVLPTHRSAYSSHGGLFQPTITQAIRLLSSHPFSSTPHPTPAPHISMSPRSPDIIDPFSSNALTYTTTGSDVFPAPSAYLSRKHSWVHIFPEGRVHQHPKKSLRYFKWGVSRLILESEPLPDIVPIFIDGNQEVMHESRQWPRFIPRAGKNIRIAFGEKVDGEKIFGDLRARWKRMVMLQKEVLIKKGQSVDWEMGELTEGLKYGEEAVALRMEVTDRVRQEVLKVRRSLGYPDEDPKQGLVETWIEEGPSAEKGKMLDGSWVGNT"


    //    SSW:
    //    topK:5 Query:UniRef100_M7V2E2
    //    AlignmentRecord(UniRef100_A0A0C3HYV1, 135M3I218M1D8M, 1449, 594, 28, 361, 28, 391, 164)


    //    our:
    //    sw.maxScore:1445.0
    //    sw.xStart:28
    //    sw.yStart:0
    //    sw.cigarX:136M3I217M1D8M
    //      sw.cigarY:136M3D217M1I8M
    val sw = new SmithWatermanAffine(query, ref, "blosum62", 11, 1)
    println("sw.maxScore:" + sw.maxScore)
    println("sw.xStart:" + sw.xStart)
    println("sw.yStart:" + sw.yStart)
    println("sw.cigarX:" + sw.cigarX)
    println("sw.cigarY:" + sw.cigarY)

  }

  test("SW 9") {
    val start = System.currentTimeMillis()
    val query = "MKFTNAIALAILAATATAVAVPEPWCGRPGQPCKREAVAVAAPVAEPWCGRPGQPCKRTPEAEAWCGRPGQPCKRDAEPWCGRPGQPCKREALPEAWCGRPGQPCKRTPLAEAEAEAWCGRPGQPCRKNKRAAEAVAEAFAEPWCGRPGQPCKRDAEADVSEAAIKRCNMVGGACFEAKRLARDLVEATAETVEDSDLFLRSLNIETREVSEVVAREAEAWCGRPGQPCKRDAEAWCGRPGQPCKREALAEAEAWCGRPGQPCKREALAEAEAWCGRPGQPCKRTAEPWCGRPGQPCKEKREADPEAEAWCGRPGQPCRAVKRAAEAIAEALAEPTAEAWCGRPGQPCKREALAEAEANAEAWCGRPGQPCRKAKRDAFALAYAADVALAQL"
    val ref = "MRLSVTAVAAILASGAIASPWCNRPGQPCKREAMPEPTLAPRLPEPEPEAWCNRPGQPCKREANPDPWCTRPGQPCKRHPEPTMAPRAPEPEPEAEPEAWCNRPGQPCKREANPDPWCTRRGQPCKRAEPTLIARVPEPAPWCTRRGQPCKRTAAPVADNVAREPVDRVIDTLANVVRSEGEIAAREPQPEPWCNRPGQPCKRTPGPDVSNAVGGSAWSAKRAIADLVQLVGRSTGNPEQYVRDLGMEEEVKRSPEAEADPWCVRPGQPCRRSIVDLSEAHEADKRWCYRPGQPCRKAKRVAEAVLGAIGSEDLAKRDESGALETIHNFAREIADM"


    //    SSW:
    //    topK:5 Query:UniRef100_M7UCQ6
    //    AlignmentRecord(UniRef100_A0A179HBS9, 32M7D41M1D8M1I19M4I15M3I13M2D14M1I3M17I19M10I11M2D14M4D15M1I11M1D4M12D24M8I10M3I25M, 635, 328, 6, 308, 6, 331, 109)


    //    our:
    //    sw.maxScore:561.0
    //    sw.xStart:6
    //    sw.yStart:2
    //    sw.cigarX:32M5D9M2D60M4I15M3I14M2D11M8I4M4I3M6I8M3I1M1I4M3I1M1I1M1I2M1I12M2D13M4D15M1I12M1D16M1I2M1I4M1I16M4D34M
    //      sw.cigarY:32M5I9M2I60M4D15M3D14M2I11M8D4M4D3M6D8M3D1M1D4M3D1M1D1M1D2M1D12M2I13M4I15M1D12M1I16M1D2M1D4M1D16M4I34M

    val sw = new SmithWatermanAffine(query, ref, "blosum62", 11, 1)
    println("sw.maxScore:" + sw.maxScore)
    println("sw.xStart:" + sw.xStart)
    println("sw.yStart:" + sw.yStart)
    println("sw.cigarX:" + sw.cigarX)
    println("sw.cigarY:" + sw.cigarY)

  }

  test("SW 10") {
    val start = System.currentTimeMillis()
    val query = "PTHIKWGD"
    val ref = "RIGRLVTRAAFTSGKVDIVAINDPFIDLNYMVYMFQYDSTHGKFKGTVKAENGKLVINGKAITIFQERDPTNIKWGDAGAEYVVESTGVFTTMEKAGAHLKGGAKRVIISAPSADAPMFVMGVNHDKYDNSLKIVSNASCTTTCLAPLAKVIHDNFGIVKGLMTTVHAITATQKTVDGPSGKLWRDGRGAAQNIIPASTGAAKAVGKVIPELNGKLTGMAFRVPTPNVSVVDLTCRLEKAAKYEDIKKVVKQASEGPLKGILGYTEDQVVSCDFKSDSHSSTFDAGAGIALNDNFVKLISWYDNEFGYSNRV"

    var sw = new SmithWatermanAffine(query, ref, "blosum50", 12, 2)
    println("sw.maxScore:" + sw.maxScore)
    println("sw.xStart:" + sw.xStart)
    println("sw.yStart:" + sw.yStart)
    println("sw.cigarX:" + sw.cigarX)
    println("sw.cigarY:" + sw.cigarY)

     sw = new SmithWatermanAffine(query, ref.substring(69,79), "blosum50", 12, 2)
    println(query)
    println(ref.substring(69,79))
    println("sw.maxScore:" + sw.maxScore)
    println("sw.xStart:" + sw.xStart)
    println("sw.yStart:" + sw.yStart)
    println("sw.cigarX:" + sw.cigarX)
    println("sw.cigarY:" + sw.cigarY)
  }


  test("SW 11") {
    val start = System.currentTimeMillis()
//    val query = "PTHIKWGD"
//    val ref = "PNHIGD"

    val query = "PNHIGD"
    val ref = "PTHIKWGD"


    var sw = new SmithWatermanAffine(query, ref, "blosum50", 12, 2)
    println("sw.maxScore:" + sw.maxScore)
    println("sw.xStart:" + sw.xStart)
    println("sw.yStart:" + sw.yStart)
    println("sw.cigarX:" + sw.cigarX)
    println("sw.cigarY:" + sw.cigarY)
    printSW(sw)

  }

  test("blosum62") {
    val score = ScoreMatrix.scoreMatrix62
    printlnArr2[Int](score)
  }


  test("char") {
    println('A'.toInt)
    val arr = "-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	-5	4".split("\\s++")
    println(arr.length)
    println(arr(0))
    println(arr(64))
    println(arr(65))
  }

  def printSW(sw: SmithWaterman): Unit = {
    println("sw.maxScore:" + sw.maxScore)
    println("sw.xStart:" + sw.xStart)
    println("sw.yStart:" + sw.yStart)
    println("sw.cigarX:" + sw.cigarX)
    println("sw.cigarY:" + sw.cigarY)

    println("sw.scoringMatrix:")
    val score = sw.scoringMatrix
    printlnArr2[Int](score)
    println("sw.moveMatrix:")
    val move = sw.moveMatrix
    printlnArr2[Char](move)
  }

  def printlnArr2[T](arr: Array[Array[T]]): Unit = {
    for (i <- 0 until (arr.length)) {
      print(i + ":\t")
      for (j <- 0 until (arr(i)).length) {
        print(arr(i)(j) + "\t")
      }
      println()
    }
  }


}
