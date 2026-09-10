def print_combined(values): #in percent, a valid suggestion should total 100
        total = 0
        for percent in values.values():
                total += percent
        
        print(f"total : {str(total)}")

def calc_mss(mss_points, values): # in mss points. any value < 0 or > 15 is invalid and would redistribute the rest i think
        print(f"mss_points : {str(mss_points)}")
        for name, percent in values.items():
                print(f"{name} : {mss_points * len(values) * (percent / 100)}")


mss_points = 11
values = { #in percent
        "mathilo" : 15,
        "colin" : 14,
        "jannis": 13,
        "julian" : 12,
        "febo" : 12,
        "sophia" : 12,
        "jan" : 11,
        "elias": 11
        }


print_combined(values)
calc_mss(mss_points, values)