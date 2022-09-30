currentSlot=`(helm get values --all yourReleaseName | grep -Po '  productionSlot: \K.*')`
if [ "$currentSlot" == "blue" ]; then
    newSlot="green"
else
    newSlot="blue"
fi


deploymentOption=$newSlot.enabled=true
helm upgrade yourReleaseName repo/blue-green --set $deploymentOption --reuse-values

deploymentOption=productionSlot=$newSlot
helm upgrade yourreleasename repo/blue-green --set $deploymentOption --reuse-values

deploymentOption=$oldSlot.enabled=false
helm upgrade yourReleaseName repo/blue-green --set $deploymentOption --reuse-values