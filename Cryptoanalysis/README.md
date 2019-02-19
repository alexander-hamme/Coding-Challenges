### Task

Given a piece of text encoded with a simple alphabetic substitution cipher, use basic cryptoanalytic techniques to recover the original plain text. You will be provided with a special dictionary file that you can read in from `"dictionary.lst"`. It will consist of one word per line.

Note: while the cipher *is* a monoalphabetic cipher, it is *not* the simplest kind of monoalphabetic cipher that just shifts all letters in the alphabet by the same number. This cipher has a one-to-one mapping of all characters, but the mapping is not defined by a simple rule. It is up to you to figure out the cipher using the input enciphered words and the list of real words from the `dictionary.lst` file.

### Input Format

The input ciphertext will consist of a string of space separated enciphered words from the provided `dictionary.lst` file that is accessible to your code. The dictionary contains technical jargon and may not be entirely representative of English language plain text, so frequency analysis techniques may not be entirely helpful.

### Constraints

1 ≤ words in dictionary.lst ≤ 1700

1 ≤ words in input ≤ 100

### Output Format

Output the decrypted plain text to *stdout*. The output should be a string of space separated words that occur in the provided dictionary. You'll be scored based on how accurately you recover the original text.

### Sample Input

`lhpohes gvjhe ztytwojmmtel lgsfcgver segpsltjyl vftstelc djfl rml catrroel jscvjqjyfo mjlesl lcjmmfqe egvj gsfyhtyq sjfgver csfaotyq lfxtyq gjywplesl lxljm dxcel mpyctyq ztytwojmmtelel mfcgv spres mjm psgvty bfml ofle mjlc dtc tygfycfctjy dfsyl zpygvel csfao yealqsjpml atyl lgsjql qyfsotelc fseyf ojllel gjzmselltyq wpyhtelc zpltgl weygel afyher rstnesl aefleo rtyhes mvflel yphe rstnes qojder dtwwer lojml mfcgvel reocfl djzder djpygtyq gstmmoeafsel reg cpdel qspyqe mflctel csflvtyq vfcl avfghtyq vftsdfool mzer rsjye wjjol psol mplvtyq catrroe mvfqe lgseey leqzeycer wjseqsjpyrer lmjtoes msjwtoel docl djpyger cjpstlcl goefy gojddesl mjrl qjddoe gjy gpdtyql lyftotyq rjayojfr swgl vjle atrqec gjzmfgces frfl qotcgver gspzd zftodjzdl lyfsh`

### Sample Output

`skulker choke minifloppies scratched recursions hairiest boas dps twiddles orthogonal posers stoppage echo cranking roached trawling saying confusers sysop bytes punting minifloppieses patch ruder pop urchin zaps lase post bit incantation barns munches trawl newsgroups wins scrogs gnarliest arena losses compressing funkiest musics fences wanked drivers weasel dinker phases nuke driver globed biffed slops patches deltas bombed bouncing cripplewares dec tubes grunge pasties trashing hats whacking hairballs pmed drone fools urls pushing twiddle phage screen segmented foregrounded spoiler profiles blts bounced tourists clean clobbers pods gobble con cubings snailing download rfcs hose widget compacter adas glitched crumb mailbombs snark`
