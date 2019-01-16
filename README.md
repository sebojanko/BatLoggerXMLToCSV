# BatLoggerXMLToCSV
A helper program to extract GPS coordinates from BatLogger XML files into a CSV which is ready for import into QGIS.

When recording a bat activity transect with a BatLogger device (http://www.batlogger.com/en), the metadata is saved as an XML file.
The file contains a lot of information (example below) but we're interested in GPS coordinates. Transforming these coordinates
into CSV makes it easy to import them into QGIS or some other GIS program to plot on a map.

### XML example

```
<BatRecord Model="M">
	<Firmware>2.3.3</Firmware>
	<SN>1831</SN>
	<Filename>18310643.wav</Filename>
	<DateTime>30.04.2018 20:07:37</DateTime>
	<Duration>1 Sec</Duration>
	<Samplerate>312500 Hz</Samplerate>
	<Temperature>18 C</Temperature>
	<BattVoltage>4.0 V</BattVoltage>
	<GPS>
		<Valid>yes</Valid>
		<Position>45.611915 16.408245</Position>
		<Altitude>136.8 m</Altitude>
		<CH1903>1298348/ 91150</CH1903>
		<HDOP>1.1</HDOP>
		<SatsUsed>7</SatsUsed>
		<GPSTimestamp>20:07:34+2h</GPSTimestamp>
		<GPSAge>3 Sec</GPSAge>
	</GPS>
	<Trigger>
		<TRIG_MODE>Crest Adv</TRIG_MODE>
		<Version>V1.0</Version>
		<Event>AutoTriggered</Event>
		<PRETRIG_TIME_MS>500</PRETRIG_TIME_MS>
		<POSTTRIG_TIME_MS>1000</POSTTRIG_TIME_MS>
		<TRIG_PAR6>7</TRIG_PAR6>
		<TRIG_PAR7>15</TRIG_PAR7>
		<TRIG_PAR8>155</TRIG_PAR8>
		<TrigValue0>5</TrigValue0>
		<TrigValue3>15</TrigValue3>
	</Trigger>
</BatRecord>
```
#### Disclaimer
I am not in any way associated with BatLogger, this is just a hobby project.
