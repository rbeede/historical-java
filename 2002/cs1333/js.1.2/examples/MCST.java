// A program to compute minimum cost spanning tree of a graph.
// Version 1.2 compliant.
// (c) 1996 duane a. bailey

import structure.*;
import java.io.*;

public class MCST {

    //+code
    static public void mcst(Graph g)
    // pre: g is a graph.
    // post: edges of minimum spanning tree of a component are visited
    {
	// keep edges ranked by length
	PriorityQueue q = new SkewHeap();
	Object v = null;	// current vertex
	Edge e;			// current edge

	g.reset();		// clear visited flags

	// select a smallest edge in graph for initial tree
	ComparableEdge shortest = null;
	Iterator ei = g.edges();
	
	v = null;
	while (ei.hasMoreElements())
	{
	    ComparableEdge possible =
		new ComparableEdge((Edge)ei.nextElement());
	    if (shortest == null ||
		possible.compareTo(shortest) < 0)
		shortest = possible;
	}
	if (shortest == null) return; // no sortest edge
	else v = shortest.here();

	// at this point v is a vertex mentioned by shortest edge
	e = null;
	while (v != null)
	{
	    // v is a (possibly new) vertex
	    if (!g.isVisited(v))
	    {
		// visit incoming edge and vertex v
		if (e != null) g.visitEdge(g.getEdge(e.here(),e.there()));
		g.visit(v);

		// now add all the outgoing edges from v
		Iterator ai = g.neighbors(v);
		while (ai.hasMoreElements()) {
		    // turn it into outgoing edge
		    e = g.getEdge(v,ai.nextElement());
		    // add the edge to the queue
		    q.add(new ComparableEdge(e));
		}
	    }
	    if (!q.isEmpty())
	    {
		// grab next shortest edge
		e = (Edge)q.remove();

		// does this edge take us somewhere new?
		// (for undirected graphs, check both ends:)
		v = e.there();
		if (g.isVisited(v)) v = e.here();
	    } else {
		// couldn't get to new vertex (we're done)
		v = null;
	    }
	}
    }
    //-code

    static public void main(String[] args)
    {
	Graph g = new GraphListDirected();

	String hereTown, thereTown;
	String startTown;
	String roadName;
	Integer length;

	// two streams.  The roadmap, and the terminal.
	ReadStream input = new ReadStream();
	// read in all roads, add two copies <-> bidirectional
	for (hereTown = input.readString();
	     !hereTown.equals("end");
	     hereTown = input.readString()) {
	
	    thereTown = input.readString();
	    roadName = input.readString();
	    length = new Integer(input.readInt());
	
	    g.add(hereTown);
	    g.add(thereTown);

	    // for getting from hereTown to thereTown
	    g.addEdge(hereTown, thereTown, length);
	    // for getting from thereTown to hereTown
	    g.addEdge(thereTown, hereTown, length);
	}

	int components = 0;
	int totalWeight = 0, totalCount = 0;
	OrderedStructure results = new OrderedList();
	while (g.size() != 0)
	{
	    mcst(g);
	    components++;

	    // we now reconstruct the minimum cost spanning minimum forest
	    Iterator ei = g.edges();
	    int weight = 0, count = 0;
	    while (ei.hasMoreElements())
	    {
		Edge e = (Edge)ei.nextElement();
		if (e.isVisited())
		{
		    weight += ((Integer)e.label()).intValue();
		    count++;
		}
	    }
	    results.add("Total weight is "+weight+" in "+count+" edges");
	    totalWeight += weight;
	    totalCount += count;
	    Iterator vi = g.elements();
	    List l = new SinglyLinkedList();
	    while (vi.hasMoreElements())
	    {
		if (g.isVisited(vi.value()))
		    l.add(vi.value());
		vi.nextElement();
	    }
	    vi = l.elements();
	    while (vi.hasMoreElements())
	    {
		if (g.contains(vi.value()))
		    g.remove(vi.value());
		vi.nextElement();
	    }
	}

	Iterator li = results.elements();
	while (li.hasMoreElements())
	{
	    System.out.println(li.nextElement());
	}
	System.out.println("Weight of "+totalCount+" edges is "+totalWeight+" over "+components+" components.");
    }
    //-code
}

/*
//+input
albany_ny binghamton_ny I88 142
albany_ny burlington_vt SR7 154
albany_ny lee_ma 90 42
albany_ny newburgh_ny I87 88
albany_ny plattsburgh_ny I87 159
albany_ny springfield_ma I90 85
albany_ny syracuse_ny I90 146
albany_ny troy_ny 787 14
albert_lea_mn des_moines_ia I35 148
albert_lea_mn madison_wi I90 262
albert_lea_mn minneapolis_mn I35 93
albert_lea_mn sioux_falls_sd I90 168
albuquerque_nm colorado_springs_co I25 365
albuquerque_nm el_paso_tx I25 257
albuquerque_nm flagstaff_az I40 323
albuquerque_nm santa_rosa_nm I40 114
allentown_pa harrisburg_pa I78 73
allentown_pa new_york_ny I78 103
allentown_pa newark_nj I78 100
allentown_pa philadelphia_pa Turnpike 60
allentown_pa scranton_pa Turnpike 72
allentown_pa stroudsburg_pa SR22 41
amarillo_tx canyon_tx I27 16
amarillo_tx oklahoma_city_ok I40 267
amarillo_tx santa_rosa_nm I40 170
asheville_nc charlotte_nc SR74 118
asheville_nc columbia_sc I26 156
asheville_nc greenville_sc SR25 62
asheville_nc knoxville_tn I40 118
asheville_nc winston_salem_nc I40 144
atlanta_ga augusta_ga I20 144
atlanta_ga birmingham_al I20 149
atlanta_ga chattanooga_tn I75 109
atlanta_ga greenville_sc I85 151
atlanta_ga macon_ga I75 82
atlanta_ga montgomery_al I85 165
atlanta_ga tallahassee_fl SR27 275
atlantic_city_nj newark_nj Turnpike 108
atlantic_city_nj philadelphia_pa Turnpike 63
atlantic_city_nj salisbury_md SR9 95
augusta_ga columbia_sc I20 79
augusta_ga greenville_sc SR25 113
augusta_ga jacksonville_fl SR1 253
augusta_ga savannah_ga SR25 133
baldwin_fl jacksonville_fl I10 19
baldwin_fl lake_city_fl I10 40
baldwin_fl waldo_fl SR301 37
baltimore_md hagerstown_md I70 75
baltimore_md harrisburg_pa I83 79
baltimore_md newark_nj Turnpike 175
baltimore_md salisbury_md SR50 111
baltimore_md washington_dc I95 37
baltimore_md wilmington_de I95 67
barstow_ca kingman_az I40 203
barstow_ca las_vegas_nv I15 155
barstow_ca san_bernadino_ca I15 74
baton_rouge_la houston_tx I10 253
baton_rouge_la jackson_ms I55 169
baton_rouge_la mobile_al I10 213
baton_rouge_la new_orleans_la I10 89
baton_rouge_la shreveport_la SR71 236
battle_creek_mi detroit_mi I96 124
battle_creek_mi fort_wayne_in I69 100
battle_creek_mi gary_in I94 128
battle_creek_mi lansing_mi I69 56
billings_mt bismark_nd I94 418
billings_mt buffalo_wy I90 161
billings_mt butte_mt I90 237
billings_mt great_falls_mt SR89 287
biloxi_ms gulfport_ms I10 15
biloxi_ms mobile_al I10 58
binghamton_ny scranton_pa I81 52
binghamton_ny syracuse_ny I81 64
birmingham_al chattanooga_tn I59 146
birmingham_al memphis_tn SR78 241
birmingham_al montgomery_al I65 90
birmingham_al nashville_tn I65 192
birmingham_al tuscaloosa_al I59 56
bismark_nd fargo_nd I94 191
bismark_nd pierre_sd SR83 211
bloomington_il champaign_il I74 52
bloomington_il chicago_il I55 133
bloomington_il davenport_ia I74 140
bloomington_il springfield_il I55 61
boise_id ogden_ut I84 323
boise_id pendleton_or I84 217
boise_id pocatello_id I84 234
boise_id spokane_wa SR95 391
boston_ma greenfield_ma SR2 99
boston_ma hartford_ct I86 102
boston_ma manchester_nh I93 51
boston_ma portland_me I95 109
boston_ma providence_ri I95 51
boston_ma springfield_ma I90 92
breezewood_pa hagerstown_md I70 47
breezewood_pa harrisburg_pa Turnpike 83
breezewood_pa morgantown_wv SR48 134
breezewood_pa pittsburgh_pa I76 121
breezewood_pa wheeling_wv I70 148
buffalo_ny erie_pa I90 108
buffalo_ny syracuse_ny I90 156
buffalo_wy casper_wy I25 305
buffalo_wy rapid_city_sd I90 207
burlington_vt white_river_jct._vt I89 96
butte_mt helena_mt I15 65
butte_mt missoula_mt I90 130
butte_mt pocatello_id I15 261
cambridge_oh charleston_wv I77 131
cambridge_oh cleveland_oh I77 116
cambridge_oh columbus_oh I70 80
cambridge_oh wheeling_wv I70 48
cambridge_oh zanesville_oh I70 26
cannon(afb)_nm canyon_tx SR60 99
cannon(afb)_nm santa_rosa_nm SR84 92
captain_cook_hi kailua_kona_hi 11 11
captain_cook_hi mountain_view_hi 11 84
casa_grande_az phoenix_az I10 58
casa_grande_az san_diego_ca I8 356
casa_grande_az tucson_az I10 63
casper_wy cheyenne_wy I25 184
casper_wy rapid_city_sd SR18 254
champaign_il chicago_il I57 133
champaign_il effingham_il I57 70
champaign_il indianapolis_in I74 114
champaign_il springfield_il I72 86
charleston_sc columbia_sc I77 95
charleston_sc florence_sc SR52 112
charleston_sc santee_sc I26 54
charleston_sc savannah_ga SR17 105
charleston_sc wilmington_nc SR17 170
charleston_wv huntington_wv I64 47
charleston_wv morgantown_wv I79 156
charleston_wv staunton_va I64 209
charleston_wv wytheville_va I77 123
charlotte_nc columbia_sc I77 95
charlotte_nc greensboro_nc I85 94
charlotte_nc greenville_sc I85 105
charlotte_nc lumberton_nc SR74 126
charlotte_nc wadesboro_nc SR74 51
charlotte_nc winston_salem_nc I85 80
charlotte_nc wytheville_va I77 134
chattanooga_tn knoxville_tn I75 109
chattanooga_tn nashville_tn I24 133
cheraw_sc columbia_sc SR1 80
cheraw_sc wadesboro_nc SR52 23
cheyenne_wy denver_co I25 98
cheyenne_wy north_platte_ne I80 223
cheyenne_wy ogden_ut I80 444
cheyenne_wy rapid_city_sd SR85 316
cheyenne_wy salt_lake_city_ut I80 457
chicago_il davenport_ia I80 177
chicago_il gary_in I90 32
chicago_il madison_wi I90 140
chicago_il milwaukee_wi I94 87
cincinnati_oh columbus_oh I71 109
cincinnati_oh dayton_oh I75 54
cincinnati_oh huntington_wv SR52 161
cincinnati_oh indianapolis_in I74 110
cincinnati_oh lexington_ky I75 78
cincinnati_oh louisville_ky I71 101
cincinnati_oh sardinia_oh I32 35
cleveland_oh columbus_oh I71 140
cleveland_oh erie_pa I90 80
cleveland_oh mercer_pa I80 94
cleveland_oh pittsburgh_pa I76 129
cleveland_oh toledo_oh I80 112
colorado_springs_co denver_co I25 67
colorado_springs_co salina_ks I70 415
columbia_sc florence_sc I20 80
columbia_sc greenville_sc I26 104
columbia_sc santee_sc I26 66
columbus_oh dayton_oh I70 71
columbus_oh huntington_wv SR23 131
columbus_oh mercer_pa I71 203
columbus_oh toledo_oh SR23 135
columbus_oh zanesville_oh I70 54
concord_nh manchester_nh I93 20
concord_nh portland_me SR4 95
concord_nh white_river_jct._vt I89 66
cove_fort_ut green_river_ut I70 158
cove_fort_ut las_vegas_nv I15 251
cove_fort_ut salt_lake_city_ut I15 178
dallas_tx ft.worth_tx I20 32
dallas_tx houston_tx I45 245
dallas_tx oklahoma_city_ok I35 205
dallas_tx san_antonio_tx I35 270
dallas_tx shreveport_la I20 195
dallas_tx texarkana_tx I30 183
davenport_ia des_moines_ia I80 160
davenport_ia gary_in I80 183
dayton_oh indianapolis_in I70 104
dayton_oh toledo_oh I75 147
daytona_beach_fl ft._lauderdale_fl I95 243
daytona_beach_fl jacksonville_fl I95 99
daytona_beach_fl orlando_fl I4 58
denver_co green_river_ut I70 350
denver_co north_platte_ne I76 269
denver_co salina_ks I70 429
des_moines_ia kansas_city_mo I35 198
des_moines_ia omaha_ne I80 128
des_moines_ia sioux_city_ia I80 183
detroit_mi flint_mi I75 67
detroit_mi gary_in I94 248
detroit_mi grand_rapids_mi I96 148
detroit_mi sault_ste._marie_mi I75 242
detroit_mi toledo_oh I75 60
dover_de salisbury_md SR13 60
dover_de washington_dc SR50 96
dover_de wilmington_de SR13 46
duluth_mn grand_forks_nd SR2 266
duluth_mn mackinaw_city_mi SR2 414
duluth_mn minneapolis_mn I35 156
duluth_mn sault_ste._marie_mi SR2 422
durham_nc greensboro_nc I85 54
durham_nc petersburg_va I85 131
durham_nc raleigh_nc SR70 27
effingham_il indianapolis_in I70 136
effingham_il mt._vernon_il I57 71
effingham_il st._louis_mo I70 101
el_paso_tx ft.worth_tx I20 588
el_paso_tx san_antonio_tx I10 552
el_paso_tx tucson_az I10 317
ellensburg_wa pendleton_or I82 233
ellensburg_wa seattle_wa I90 103
ellensburg_wa spokane_wa I90 171
erie_pa mercer_pa I79 66
fargo_nd grand_forks_nd I29 80
fargo_nd minneapolis_mn I94 240
fargo_nd sioux_falls_sd I29 261
flagstaff_az kingman_az I40 159
flagstaff_az phoenix_az I17 135
flint_mi lansing_mi I69 51
flint_mi standish_mi SR23 73
flint_mi toledo_oh SR23 106
florence_sc lumberton_nc I95 53
florence_sc rocky_mount_nc I95 165
florence_sc santee_sc I95 70
fort_walton_bch_fl panama_city_fl SR98 55
fort_walton_bch_fl pensacola_fl SR98 40
fort_wayne_in upland_in I69 51
ft._lauderdale_fl ft._myers_fl I75 99
ft._lauderdale_fl miami_fl I95 27
ft._myers_fl port_charlotte_fl I75 22
ft.smith_ar little_rock_ar I40 153
ft.smith_ar oklahoma_city_ok I40 179
ft.smith_ar texarkana_tx SR71 181
ft.smith_ar tulsa_ok Turnpike 116
ft.worth_tx oklahoma_city_ok I35 203
ft.worth_tx san_antonio_tx I35 267
gainesville_fl lake_city_fl I75 42
gainesville_fl tampa_fl I75 129
gainesville_fl waldo_fl SR24 19
gary_in grand_rapids_mi I196 144
gary_in indianapolis_in I65 145
gary_in south_bend_in I90 66
grand_forks_nd minot_nd SR2 215
grand_rapids_mi sault_ste._marie_mi SR131 276
great_falls_mt havre_mt SR87 113
great_falls_mt helena_mt I15 91
green_bay_wi madison_wi SR151 131
green_bay_wi milwaukee_wi I43 116
green_bay_wi sault_ste._marie_mi SR41 285
green_river_ut salt_lake_city_ut SR6 176
greenfield_ma north_adams_ma SR2 28
greenfield_ma springfield_ma 91 38
greensboro_nc roanoke_va SR220 109
greensboro_nc winston_salem_nc I40 30
gulfport_ms hattisburg_ms SR49 66
gulfport_ms new_orleans_la I10 80
hagerstown_md harrisburg_pa I81 77
hagerstown_md morgantown_wv SR48 133
hagerstown_md strasburg_va I81 51
hagerstown_md washington_dc I270 64
hanamaulu_hi kapaa_hi SR50 5
hanamaulu_hi lihue_hi SR50 2
harrisburg_pa philadelphia_pa I76 105
harrisburg_pa scranton_pa I81 115
hartford_ct new_haven_ct I91 40
hartford_ct newburgh_ny I84 101
hartford_ct providence_ri SR6 74
hartford_ct springfield_ma I91 30
hattisburg_ms jackson_ms SR49 89
hattisburg_ms meridian_ms I59 86
hattisburg_ms mobile_al SR98 106
hattisburg_ms new_orleans_la I59 98
havre_mt minot_nd SR2 439
helena_mt missoula_mt SR12 124
hilo_hi kailua_kona_hi 19 87
hilo_hi mountain_view_hi 11 15
honolulu_hi kaneohe_hi 63 9
honolulu_hi pearl_city_hi H1 12
honolulu_hi waikiki_beach_hi H1 1
houston_tx san_antonio_tx I10 197
houston_tx shreveport_la SR59 226
huntington_wv lexington_ky I64 112
indianapolis_in louisville_ky I65 114
indianapolis_in upland_in I69 59
iron_mountain_mi mackinaw_city_mi SR2 197
iron_mountain_mi minneapolis_mn I8 219
jackson_ms little_rock_ar SR65 267
jackson_ms meridian_ms I20 90
jackson_ms new_orleans_la I55 183
jackson_ms shreveport_la I20 213
jackson_ms winona_ms I55 116
jacksonville_fl santee_sc I95 232
jacksonville_fl savannah_ga I95 143
jacksonville_fl tampa_fl SR301 198
kahului_hi kihei_hi 311 6
kahului_hi wailuku_hi 32 2
kailua_kona_hi kamuela_hi 190 39
kalaheo_hi kekaha_hi SR50 13
kalaheo_hi lihue_hi SR50 12
kansas_city_mo omaha_ne I29 184
kansas_city_mo st._louis_mo I70 254
kansas_city_mo topeka_ks I70 60
kansas_city_mo tulsa_ok SR169 259
kansas_city_mo wichita_ks I35 201
kaunakakai_hi waialua_hi 450 19
kingman_az las_vegas_nv SR93 103
kingman_az phoenix_az SR93 184
knoxville_tn lexington_ky I75 173
knoxville_tn nashville_tn I40 170
knoxville_tn wytheville_va I81 187
lahaina_hi wailuku_hi 340 30
lake_city_fl macon_ga I75 199
lake_city_fl orlando_fl I75 160
lake_city_fl tallahassee_fl I10 98
lake_city_fl tampa_fl I75 169
las_vegas_nv reno_nv SR395 446
lee_ma pittsfield_ma SR7 11
lee_ma springfield_ma 90 44
lexington_ky louisville_ky I64 78
lexington_ky nashville_tn I65 214
lexington_ky paducah_ky Turnpike 262
little_rock_ar memphis_tn I40 138
little_rock_ar springfield_mo SR65 218
little_rock_ar texarkana_tx I30 130
los_angeles_ca sacramento_ca I5 382
los_angeles_ca san_bernadino_ca I10 40
los_angeles_ca san_diego_ca I5 127
los_angeles_ca san_francisco_ca I5 387
louisville_ky mt._vernon_il I64 190
louisville_ky nashville_tn I65 180
louisville_ky paducah_ky Turnpike 220
lumberton_nc raleigh_nc I95 95
lumberton_nc rocky_mount_nc I95 126
lumberton_nc wilmington_nc SR74 80
mackinaw_city_mi oscoda_mi SR23 149
mackinaw_city_mi sault_ste._marie_mi I75 57
mackinaw_city_mi standish_mi I75 154
macon_ga montgomery_al SR80 180
macon_ga savannah_ga I16 166
macon_ga tallahassee_fl SR319 190
madison_wi milwaukee_wi I94 77
madison_wi minneapolis_mn I94 271
madison_wi springfield_il SR51 290
marion_il mt._vernon_il I57 53
marion_il paducah_ky I24 42
marion_il sikeston_mo I57 60
medart_fl panama_city_fl SR98 70
medart_fl tallahassee_fl SR319 28
memphis_tn nashville_tn I40 219
memphis_tn sikeston_mo I55 142
memphis_tn winona_ms I55 96
mercer_pa pittsburgh_pa I79 61
mercer_pa stroudsburg_pa I80 303
meridian_ms mobile_al SR45 129
meridian_ms montgomery_al SR80 160
meridian_ms tuscaloosa_al I59 88
miami_fl orlando_fl Turnpike 236
miami_fl tampa_fl SR41 266
missoula_mt spokane_wa I90 201
mobile_al montgomery_al I65 171
mobile_al new_orleans_la I10 153
mobile_al pensacola_fl I10 59
montgomery_al pensacola_fl I65 159
montgomery_al tallahassee_fl SR231 208
montgomery_al tuscaloosa_al SR82 95
morgantown_wv pittsburgh_pa I79 69
morgantown_wv wheeling_wv I79 79
mt._vernon_il scott_afb_il I64 55
nashville_tn paducah_ky I24 148
new_haven_ct new_york_ny I95 70
new_haven_ct providence_ri I95 102
new_york_ny newark_nj I95 19
new_york_ny newburgh_ny I87 66
new_york_ny stroudsburg_pa I80 78
newark_nj newburgh_ny I87 69
newark_nj philadelphia_pa Turnpike 82
newark_nj stroudsburg_pa I80 66
newburgh_ny scranton_pa I84 96
norfolk_va richmond_va I64 99
norfolk_va rocky_mount_nc SR58 136
norfolk_va salisbury_md SR13 129
norfolk_va wilmington_nc SR17 259
north_adams_ma williamstown_ma SR2 5
north_platte_ne omaha_ne I80 299
north_platte_ne pierre_sd SR83 267
ogden_ut pocatello_id I15 132
ogden_ut salt_lake_city_ut I15 34
oklahoma_city_ok tulsa_ok Turnpike 103
oklahoma_city_ok wichita_ks I35 158
omaha_ne sioux_city_ia I29 98
orlando_fl tampa_fl I4 84
oscoda_mi standish_mi SR23 57
paducah_ky sikeston_mo SR60 59
pearl_city_hi wahiawa_hi H2 8
pendleton_or portland_or I84 211
pendleton_or spokane_wa SR395 203
pensacola_fl tallahassee_fl I10 200
petersburg_va richmond_va I95 24
petersburg_va rocky_mount_nc I95 101
philadelphia_pa wilmington_de I95 29
phoenix_az san_bernadino_ca I10 334
pierre_sd rapid_city_sd I90 192
pierre_sd sioux_falls_sd I90 222
pittsburgh_pa wheeling_wv I70 57
pittsfield_ma williamstown_ma SR7 20
plattsburgh_ny watertown_ny I3 170
port_charlotte_fl tampa_fl I75 93
portland_or seattle_wa I5 173
portland_or weed_ca I5 363
raleigh_nc rocky_mount_nc SR64 51
rapid_city_sd sioux_falls_sd I90 346
reno_nv sacramento_ca I80 134
reno_nv salt_lake_city_ut I80 531
reno_nv weed_ca SR395 265
richmond_va staunton_va I64 106
richmond_va washington_dc I95 114
roanoke_va staunton_va I81 81
roanoke_va wytheville_va I81 67
sacramento_ca san_francisco_ca I80 90
sacramento_ca weed_ca I5 227
salina_ks topeka_ks I70 114
salina_ks wichita_ks I135 87
salisbury_md washington_dc SR50 120
san_bernadino_ca san_diego_ca I15 115
san_francisco_ca weed_ca I5 282
santee_sc savannah_ga I95 96
scott_afb_il st._louis_mo I64 17
scranton_pa stroudsburg_pa I380 51
shreveport_la texarkana_tx SR71 70
sikeston_mo springfield_mo SR60 241
sikeston_mo st._louis_mo I55 152
sioux_city_ia sioux_falls_sd I29 85
south_bend_in toledo_oh I90 126
springfield_il st._louis_mo I55 94
springfield_ma white_river_jct._vt I91 118
springfield_mo st._louis_mo I44 214
springfield_mo tulsa_ok I44 180
staunton_va strasburg_va I81 76
strasburg_va washington_dc I66 72
syracuse_ny watertown_ny I81 74
tallahassee_fl tampa_fl SR19 252
topeka_ks wichita_ks Turnpike 142
troy_ny williamstown_ma SR2 34
tulsa_ok wichita_ks Turnpike 175
tuscaloosa_al winona_ms SR82 144
wadesboro_nc winston_salem_nc SR52 100
winston_salem_nc wytheville_va SR52 83
end
//-input
//+output
Total weight is 149 in 4 edges
Total weight is 19 in 1 edges
Total weight is 20084 in 215 edges
Total weight is 30 in 4 edges
Total weight is 32 in 4 edges
Total weight is 38 in 3 edges
Weight of 231 edges is 20352 over 6 components.
//-output
*/
