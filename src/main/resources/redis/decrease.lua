local key=KEYS[1];
local subNum = tonumber(ARGV[1]);
local surplusStock = tonumber(redis.call('get',key));
if (surplusStock<=0 or subNum > surplusStock) then return 0
else
	redis.call('incrby', KEYS[1], -subNum)
    return 1
end
