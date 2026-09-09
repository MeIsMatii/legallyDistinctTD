def print_combined(values):
        total = 0
        for percent in values.values():
                total += percent
        
        print("total : " + str(total))

def calc_mss(mss_points, values):
        print("mss_points : " + str(mss_points))
        for name, percent in values.items():
                print(name + " : " +  str(mss_points * len(values) * (percent/100)))


mss_points = 11

mathilo = 15
colin = 14
jannis = 13
julian = 12
febo = 12
sophia = 12
jan = 11
elias = 11


values = {
        "mathilo" : mathilo,
        "colin" : colin,
        "jannis": jannis,
        "julian" : julian,
        "febo" : febo,
        "sophia" : sophia,
        "jan" : jan,
        "elias": elias
        }


#print_combined(values)
calc_mss(mss_points, values)