typ=$1
api=$2
url=http://127.0.0.1:8080/api/$api
case $typ in
	"POST") curl -X $typ \
		-H 'Content-type:application/json' \
		$url --data $3
			;;
	"GET") curl -X $typ -H \'Content-Type:application/json\' \
                $url
			;;
	"PUT") echo curl -X $typ \
		-H \'Content-type:application/json\' \
		$url --data $3
			;;
	"DELETE") curl -X $typ -H 'Content-Type:application/json' \
		$url
			;;
esac
echo \r
