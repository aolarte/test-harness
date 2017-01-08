print" ${args[0]} ".replaceAll(/([\D])2016([\D])/){b,c,e->"${c}2017$e"}[1..-1]
